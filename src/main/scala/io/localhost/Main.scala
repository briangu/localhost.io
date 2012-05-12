package io.localhost

import io.viper.common.{StaticFileContentInfoProviderFactory, NestServer, ViperServer}


object Main {
  def main(args: Array[String]) {
    StaticFileContentInfoProviderFactory.enableCache(false)
    NestServer.run(8080, new ViperServer(args(0)))
  }
}

