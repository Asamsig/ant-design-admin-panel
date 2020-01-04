package no.samsig

import typings.antd.{antdProps, antdComponents => AntDesign}
import typings.react.ScalableSlinky._
import typings.antd.AntdFacade._
import typings.antd.antdComponents.MenuDividerProps
import typings.rcDashMenu.libMenuItemGroupMod.MenuItemGroupProps
import typings.rcDashMenu.libMod.MenuItemGroup
import typings.react.reactMod.ComponentType

/**
  * Created by Alexander on 27/12/2019
  */
object AntDesignFacade {

  @scala.inline
  def AntDesignMenuItemGroup: ComponentType[MenuItemGroupProps] = typings.antd.antdMod.Menu.ItemGroup.asInstanceOf[typings.react.reactMod.ComponentType[MenuItemGroupProps]]

  @scala.inline
  def MenuItemGroupProps: typings.rcDashMenu.libMenuItemGroupMod.MenuItemGroupProps.type = typings.rcDashMenu.libMenuItemGroupMod.MenuItemGroupProps
//  type MenuItemGroupProps = MenuItemGroupProps

  @inline def MenuDivider: ExternalComponentP[MenuDividerProps] = importSTComponent(AntDesign.MenuDivider)
  @inline def MenuItemGroup: ExternalComponentP[MenuItemGroupProps] = importSTComponent(AntDesignMenuItemGroup)

}
