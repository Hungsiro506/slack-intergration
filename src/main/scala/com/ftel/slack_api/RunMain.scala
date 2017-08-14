package com.ftel.slack_api

/**
  * Created by hungdv on 12/08/2017.
  */
import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * run RunMain to send the message "welcome user on slack!! :)" on slack channel named notify-me as user
  */
object RunMain extends App {

  val slackServiceImpl = SlackServiceImpl
  val result = Await.result(slackServiceImpl.sendSlackMsg("general", "welcome user on slack!! :)", Some("NOC-bot")), Duration(30, "seconds"))

}