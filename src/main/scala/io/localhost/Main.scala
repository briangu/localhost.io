package io.localhost

import io.viper.common._
import jpbetz.cli._


object Main {
  def main(args: Array[String]) {
    val app: CommandSet = new CommandSet("localhost")
    app.addSubCommand(classOf[DirectoryServerCommand])
    app.addSubCommand(classOf[VirtualServerCommand])
    app.invoke(args)
  }
}

@SubCommand(name = "dir", description = "Directory server")
class DirectoryServerCommand extends Command {
  @Arg(name = "path", optional = false) var _path: String = null
  @Opt(opt = "r", longOpt = "port", description = "port", required = false) private var _port: Number = 8080

  def exec(commandLine: CommandContext) {
    StaticFileContentInfoProviderFactory.enableCache(false)
    NestServer.run(_port.intValue(), new ViperServer(_path))
  }
}

@SubCommand(name = "jar", description = "Load VirtualServers from jars")
class VirtualServerCommand extends Command {
  @Arg(name = "path", optional = false) var _path: String = null
  @Opt(opt = "r", longOpt = "port", description = "port", required = false) private var _port: Number = 8080

  def exec(commandLine: CommandContext) {
    new DynamicContainer(_port.intValue(), _path).run
  }
}

