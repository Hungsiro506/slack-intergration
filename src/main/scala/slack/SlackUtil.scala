package slack

/**
  * Created by hungdv on 14/08/2017.
  */
import slack.models.Message

object SlackUtil {

  private val mentionrx = """<@(\w+)>""".r

  def extractMentionedIds(text: String): Seq[String] = {
    mentionrx.findAllMatchIn(text).toVector.map(_.subgroups.head)
  }

  def mentionsId(text: String, id: String): Boolean = {
    mentionrx.findAllMatchIn(text).toVector.map(_.subgroups.head).contains(id)
  }

  def isDirectMsg(m: Message): Boolean = {
    m.channel.startsWith("D")
  }
}