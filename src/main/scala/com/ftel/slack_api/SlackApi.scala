package com.ftel.slack_api

/**
  * Created by hungdv on 12/08/2017.
  */

import akka.actor.ActorSystem
import slack.api.MySlackApiClient

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

trait SlackApi {
  val token: String = "xoxp-225820570852-226651607078-225943213413-c11c8d2595bb473c0bb9251e6d5e50ec" // replace your Slack API token here
  val proxyHost = "172.30.45.220"
  val porxyPort = 80
  //val token: String = "xoxp-225820570852-226651607078-226852598423-0dc3a59d75cab9158e6bd4c935e74e43" // replace your Slack API token here
  val apiClient: MySlackApiClient
  implicit val system = ActorSystem("slack")
  /**
    *
    * this method sends a msgBody on the channel named channelName as user
    */
  def send(channelName: String, msgBody: String, user: Option[String]): Future[Boolean] = {
    val channelId: Future[Option[String]] = getChannelId(channelName)
    channelId.map {
      _ match {
        case Some(channelId) =>
          apiClient.postChatMessage(channelId, msgBody, user)
          true
        case None =>
          false

      }
    }
  }

  /**
    *
    * this method returns the channel id of the channel named channelName
    */
  def getChannelId(channelName: String): Future[Option[String]] = {
    val channelsFuture: Future[Seq[Option[String]]] = apiClient.listChannels().map(channels =>
      channels.map(channel =>
        if (channel.name == channelName) {
          Some(channel.id)
        }
        else {
          None
        }
      ))
    channelsFuture.map(channels => channels.find(channel => channel.isDefined).flatten)
  }
}

object SlackApiImpl extends SlackApi {
  val apiClient: MySlackApiClient = MySlackApiClient(token,"172.30.45.220",80)
}