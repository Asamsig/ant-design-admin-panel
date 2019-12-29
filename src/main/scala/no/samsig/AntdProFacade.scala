package no.samsig

import typings.atAntDashDesignProDashLayout.{atAntDashDesignProDashLayoutComponents => AntdPro, atAntDashDesignProDashLayoutProps}
import slinky.core.TagMod
import slinky.core.facade.ReactElement
import typings.antd.libFormFormMod.default.{create => createForm}
import typings.antd.libFormFormMod.{FormCreateOption, GetFieldDecoratorOptions, WrappedFormUtils}
import typings.react.ScalableSlinky._
import typings.react.reactMod.ComponentType

import scala.scalajs.js
/**
 * Created by Alexander on 27/12/2019
 */
object AntdProFacade extends atAntDashDesignProDashLayoutProps {

  @inline def ProLayout: ExternalComponentP[ProDashLayoutProps] = importSTComponent(AntdPro.ProDashLayout)

}
