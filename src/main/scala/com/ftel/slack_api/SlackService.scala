package com.ftel.slack_api

/**
  * Created by hungdv on 12/08/2017.
  */

import scala.concurrent.Future


trait SlackService {

  val slackApi: SlackApi

  /**
    *
    * this method calls the send(..) method of the SlackApi
    */
  def sendSlackMsg(channelName: String, msgBody: String, user: Option[String]): Future[Boolean] = {
    slackApi.send(channelName, msgBody, user)
  }
}

object SlackServiceImpl extends SlackService {
  val slackApi: SlackApi = SlackApiImpl
}