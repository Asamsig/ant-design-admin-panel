package no.samsig

import no.samsig.AntDesignProFacade.{Login => AntdLogin, _}
import no.samsig.Assets.{ReactLogo, ScalaJsLogo}
import org.scalajs.dom.console
import org.scalajs.dom.ext.Ajax
import slinky.core._
import slinky.core.annotations.react
import slinky.core.facade.Hooks._
import slinky.core.facade.SetStateHookCallback
import slinky.web.html._
import typings.antd.AntdFacade._
import typings.antd.libPaginationPaginationMod.PaginationConfig
import typings.antd.libTableInterfaceMod.{ColumnFilterItem, SorterResult, TableCurrentDataSource}
import typings.atAntDashDesignProDashLayout.MenuDataItemisUrlboolean
import typings.csstype.csstypeMod.{FloatProperty, StandardLonghandProperties}
import typings.react.ScalableSlinky._
import typings.react.reactMod.{CSSProperties, ReactNode}
import typings.reactDashRouterDashDom.ReactRouterFacade._
import typings.std
import typings.std.fetch

import scala.collection.mutable
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import scala.scalajs.js.{JSON, |}
import scala.util.Random

class TableItem(val name: String, val gender: String, val email: String) extends js.Object
@react class MyTable extends Component {

  type Props = Unit

  case class State(data: js.Array[TableItem], isLoading: Boolean, pagination: mutable.Map[String, Object])

  def initialState = State(js.Array(), true, mutable.Map.empty[String, Object])

//  handleTableChange = (pagination, filters, sorter) => {
//    const pager = { ...this.state.pagination };
//    pager.current = pagination.current;
//    this.setState({
//      pagination: pager,
//    });
//    this.fetch({
//      results: pagination.pageSize,
//      page: pagination.current,
//      sortField: sorter.field,
//      sortOrder: sorter.order,
//      ...filters,
//    });
//  };
//

  def fetchData(queryParams: mutable.Map[String, Object]) = {
    //  fetch = (params = {}) => {
    //    console.log('params:', params);
    //    this.setState({ loading: true });
    //    reqwest({
    //      url: 'https://randomuser.me/api',
    //      method: 'get',
    //      data: {
    //        results: 10,
    //        ...params,
    //      },
    //      type: 'json',
    //    }).then(data => {
    //      const pagination = { ...this.state.pagination };
    //      // Read total count from server
    //      // pagination.total = data.totalCount;
    //      pagination.total = 200;
    //      this.setState({
    //        loading: false,
    //        data: data.results,
    //        pagination,
    //      });
    //    });
    //  };
    Ajax.get(s"https://randomuser.me/api?seed=samsig&nat=no,dk" + queryParams.map { case (key, value) => s"$key=$value" }.mkString("&", "&", "")).map {
      case data =>
        val pagination = state.pagination
        pagination.put("total", 200)
        val data1 = JSON.parse(data.responseText).results.asInstanceOf[js.Array[js.Dynamic]]
        setState(State(data1.map(obj => new TableItem(s"${obj.name.first} ${obj.name.last}", obj.gender.toString, obj.email.toString)), false, pagination))
    }
  }
  def handleTableChange(pagination: PaginationConfig,
                        filters: std.Partial[std.Record[String, Array[String]]],
                        sorter: SorterResult[TableItem],
                        extra: TableCurrentDataSource[TableItem]) = {
//    handleTableChange = (pagination, filters, sorter) => {
//      const pager = { ...this.state.pagination };
//      pager.current = pagination.current;
//      this.setState({
//        pagination: pager,
//      });
//      this.fetch({
//        results: pagination.pageSize,
//        page: pagination.current,
//        sortField: sorter.field,
//        sortOrder: sorter.order,
//        ...filters,
//      });
//    };
    val pager = state.pagination
    pager.put("current", pagination.current)
    setState(_.copy(pagination = pager))
    fetchData(
      mutable.Map(
        "results"   -> pagination.pageSize,
        "page"      -> pagination.current,
        "sortField" -> sorter.field,
        "sortOrder" -> sorter.order,
      )
    )
  }

  override def componentDidMount(): Unit = {
    fetchData(mutable.Map("results" -> 10, "page" -> 1))
  }

  def render() = {
    Table[TableItem](
      TableProps[TableItem](
        columns = { columns },
        rowKey = generateRowKey,
        dataSource = state.data,
        pagination = this.state.pagination.asInstanceOf[PaginationConfig],
        loading = state.isLoading,
        onChange = { this.handleTableChange }
      )
    )
  }

  private val generateRowKey: js.Function2[TableItem, Double, String] = (tableItem: TableItem, double: Double) => {
    Random.nextString(10)
  }

  private val columns: js.Array[ColumnProps[TableItem]] = js.Array(
    ColumnProps[TableItem](
      title = "Name",
      dataIndex = "name",
      sorter = true,
      width = "20%"
    ),
    ColumnProps[TableItem](
      title = "Gender",
      dataIndex = "gender",
      filters = js.Array(
        ColumnFilterItem(value = "male", text = "Male"),
        ColumnFilterItem(value = "female", text = "Female"),
      ),
      width = "20%"
    ),
    ColumnProps[TableItem](
      title = "Email",
      dataIndex = "email",
    ),
  )
}
