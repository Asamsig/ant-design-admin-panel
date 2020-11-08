package no.samsig

import org.scalablytyped.runtime.StringDictionary
import org.scalajs.dom.ext.Ajax
import slinky.core._
import slinky.core.annotations.react
import typings.antd.AntdFacade._
import typings.antd.libPaginationPaginationMod.PaginationConfig
import typings.antd.libTableInterfaceMod.{ColumnFilterItem, SorterResult, TableCurrentDataSource}
import typings.std

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js
import scala.scalajs.js.JSON
import scala.util.Random

class TableItem(val name: String, val gender: String, val email: String, val nationality: String) extends js.Object
@react class MyTable extends Component {

  type Props = Unit

  case class State(data: js.Array[TableItem] = js.Array(), isLoading: Boolean = true, pagination: js.Dynamic = js.Dynamic.literal(total = 200))

  def initialState = State()

  def fetchData(queryParams: Map[String, Any]) = {
    setState(_.copy(isLoading = true))
    Ajax.get("https://randomuser.me/api?seed=samsig&nat=no,dk" + queryParams.map { case (key, value) => s"$key=$value" }.mkString("&", "&", "")).map { data =>
      val results = JSON.parse(data.responseText).results.asInstanceOf[js.Array[js.Dynamic]]
      setState(
        _.copy(
          results.map(obj => new TableItem(s"${obj.name.first} ${obj.name.last}", obj.gender.toString, obj.email.toString, obj.nat.toString)),
          isLoading = false
        )
      )
    }
  }

  def handleTableChange(pagination: PaginationConfig,
                        filters: std.Partial[std.Record[String, js.Array[String]]],
                        sorter: SorterResult[TableItem],
                        extra: TableCurrentDataSource[TableItem]) = {
    val pager = state.pagination
    pager.current = pagination.current
    setState(_.copy(pagination = pager))
    val nationalityFilter     = filters.asInstanceOf[StringDictionary[js.Array[String]]].get("nationality")
    val filterByNationalities = nationalityFilter.filterNot(_.isEmpty).getOrElse(js.Array("dk", "no"))
    val nationalities         = defaultNationalities.filter(filterByNationalities.contains)

    import scala.scalajs.js.UndefOr.any2undefOrA
    val sortByField = any2undefOrA(sorter.asInstanceOf[js.Dynamic].field).toOption
    val sortOrder   = any2undefOrA(sorter.asInstanceOf[js.Dynamic].order).toOption
    fetchData(
      Map(
        "results" -> pagination.pageSize,
        "page"    -> pagination.current,
      )
        ++ Map("nat" -> nationalities.mkString(","))
        ++ sortByField
          .map(field => Map("sortField" -> field.toString))
          .getOrElse(Map.empty)
        ++ sortOrder
          .map(order => Map("sortOrder" -> order.toString))
          .getOrElse(Map.empty)
    )
  }
  private val defaultNationalities = js.Array("dk", "no")

  override def componentDidMount(): Unit = {
    fetchData(Map("nat" -> defaultNationalities.mkString(","), "results" -> 10, "page" -> 1))
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
      width = "20%"
    ),
    ColumnProps[TableItem](
      title = "Email",
      dataIndex = "email",
    ),
    ColumnProps[TableItem](
      title = "Nationality",
      dataIndex = "nationality",
      filters = js.Array(
        ColumnFilterItem(value = "dk", text = "Danish"),
        ColumnFilterItem(value = "no", text = "Norwegian"),
      ),
    ),
  )
}
