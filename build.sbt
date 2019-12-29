enablePlugins(ScalaJSBundlerPlugin)

name := "ant-design-admin-panel"

scalaVersion := "2.12.8"

npmDependencies in Compile += "react" -> "16.8.6"
npmDependencies in Compile += "react-dom" -> "16.8.6"
npmDependencies in Compile += "react-proxy" -> "1.1.8"
npmDependencies in Compile += "react-router-dom" -> "5.1.2"
npmDependencies in Compile += "antd" -> "3.26.3"
npmDependencies in Compile += "ant-design-pro" -> "2.3.2"

npmDevDependencies in Compile += "file-loader" -> "3.0.1"
npmDevDependencies in Compile += "style-loader" -> "0.23.1"
npmDevDependencies in Compile += "css-loader" -> "2.1.1"
npmDevDependencies in Compile += "html-webpack-plugin" -> "3.2.0"
npmDevDependencies in Compile += "copy-webpack-plugin" -> "5.0.2"
npmDevDependencies in Compile += "webpack-merge" -> "4.2.1"

resolvers += Resolver.bintrayRepo("oyvindberg", "ScalablyTyped")

libraryDependencies += "me.shadaj" %%% "slinky-web" % "0.6.3"
libraryDependencies += "me.shadaj" %%% "slinky-hot" % "0.6.3"
libraryDependencies += ScalablyTyped.A.antd
libraryDependencies += ScalablyTyped.A.`ant-design__pro-layout`
libraryDependencies += ScalablyTyped.A.`antd-slinky-facade`
libraryDependencies += ScalablyTyped.R.`react-router-dom-slinky-facade`
libraryDependencies += ScalablyTyped.R.`react-router-dom`
libraryDependencies += "org.scalatest" %%% "scalatest" % "3.0.5" % Test

scalacOptions += "-P:scalajs:sjsDefinedByDefault"
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

version in webpack := "4.29.6"
version in startWebpackDevServer:= "3.2.1"

webpackResources := baseDirectory.value / "webpack" * "*"

webpackConfigFile in fastOptJS := Some(baseDirectory.value / "webpack" / "webpack-fastopt.config.js")
webpackConfigFile in fullOptJS := Some(baseDirectory.value / "webpack" / "webpack-opt.config.js")
webpackConfigFile in Test := Some(baseDirectory.value / "webpack" / "webpack-core.config.js")

webpackDevServerExtraArgs in fastOptJS := Seq("--inline", "--hot")
webpackBundlingMode in fastOptJS := BundlingMode.LibraryOnly()

requireJsDomEnv in Test := true

addCommandAlias("dev", ";fastOptJS::startWebpackDevServer;~fastOptJS")

addCommandAlias("build", "fullOptJS::webpack")