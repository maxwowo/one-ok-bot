package bot.listeners

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class MessageListener : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val builder = EmbedBuilder()
        val content = event.message.contentRaw.toLowerCase()
        val messageNotFromSelf = event.author != event.jda.selfUser
        val selfTaggedInMessage = event.jda.selfUser in event.message.mentionedUsers
        val goodBotInMessage = "good\\s+bot".toRegex().containsMatchIn(content)
        val eitherInMessage = "(good|bad)\\s+bot".toRegex().containsMatchIn(content)

        if (messageNotFromSelf && selfTaggedInMessage && eitherInMessage) {
            if (goodBotInMessage) {
                builder.setDescription(
                    "Damn right I am, why dont you [chuck me a star](https://github.com/maxwowo/one-ok-bot) huh?",
                )
            } else {
                builder.setDescription(
                    javaClass.getResource("/copypastas/navy_seal.md").readText(),
                )
            }

            event.textChannel.sendMessage(builder.build()).queue()
        }
    }
}
