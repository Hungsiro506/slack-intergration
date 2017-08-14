package com.ftel.slack_api

import akka.actor.ActorSystem
import akka.actor.Status.{Failure, Success}
import slack.api.{MyBlockingSlackApiClient, MySlackApiClient, SlackApiClient}
import slack.rtm.MySlackRtmClient
import akka.actor.ActorSystem
import slack.models.Message

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
/**
  * Created by hungdv on 12/08/2017.
  */
object HelloWorld {
  def main(args: Array[String]): Unit = {
    System.setProperty("http.proxyHost", "172.30.45.220")
    System.setProperty("http.proxyPort", "80")
    val token = ""


    println("Create new client")
    implicit val system = ActorSystem("slack")
/*    val blockingClient = MyBlockingSlackApiClient(token,5.seconds,"172.30.45.220",80)
    val channels_list = blockingClient.listChannels()
    println(channels_list)*/
    //val client = MySlackApiClient(token,"172.30.45.220",80)
    val rtm_client = MySlackRtmClient(token,10.seconds,"172.30.45.220",80)
    implicit val ec = system.dispatcher

    val state = rtm_client.state
    val selfId = state.self.id



    val generalChanId = state.getChannelIdForName("general").get
    println(generalChanId)

    rtm_client.sendMessage(generalChanId, "Hello!")

    rtm_client.sendMessage("C6MNBSL9Y","test")
/*
    rtm_client.onMessage { message =>
      println(s"User: ${message.user}, Message: ${message.text}")
    }*/
    rtm_client.onEvent{
      case e: Message => println(e.user + " " + e.text)
    }

    /*val channels = client.listChannels().map { channels =>
      channels.map {
        channel =>
          println(channel.name)
          if (channel.name == "general") {
            Some(channel.id)
          }
          else {
            None
          }

      }

    }
    val channelID = channels.map(channels => channels.find(channel => channel.isDefined).flatten)
    channelID.map{
      _ match {
        case Some(channelID) =>    { client.postChatMessage(channelID,"Hello slack",Some("Sky-net"))

        }

        case None => println("not found channel")
      }
    }*/

  }
}
