package com.ftel.slack_api

import akka.actor.ActorSystem
import slack.api.MySlackApiClient
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.util.{Failure, Success}

/**
  * Created by hungdv on 12/08/2017.
  */
object Test {


/*  System.setProperty("https.proxyHost", "172.30.45.220")
  System.setProperty("https.proxyPort", "80")
  System.setProperty("java.net.useSystemProxies", "true")*/
  val token = "xoxp-225820570852-226651607078-225943213413-c11c8d2595bb473c0bb9251e6d5e50ec"
  //val token = "xoxp-225820570852-226651607078-226855333319-db275eb9250b1598f76c7766e1b3b6c4"
  val client = MySlackApiClient(token,"172.30.45.220",80)

  implicit val system = ActorSystem("slack")


  def main(args: Array[String]): Unit = {
    val res = client.listChannels()

    Thread.sleep(1000)

    res.onComplete {
      case Success(channels) =>  {
        println(channels)
      }
      case Failure(err) => {
        println(err)
      }
    }
  }
}
