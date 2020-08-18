package bot.listeners

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class MessageListener : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val builder = EmbedBuilder()
        val content = event.message.contentRaw.toLowerCase()

        if (event.author != event.jda.selfUser && event.jda.selfUser in event.message.mentionedUsers) {
            if ("good\\s+bot".toRegex().containsMatchIn(content)) {
                builder.setDescription(
                    "Damn right I am, why dont you [chuck me a star](https://github.com/maxwowo/one-ok-bot) huh?"
                )
            } else if ("bad\\s+bot".toRegex().containsMatchIn(content)) {
                builder.setDescription(
                    javaClass.getResource("/copypastas/navy_seal.txt").readText()
                )
            }

            event.textChannel.sendMessage(builder.build()).queue()
        }
    }
}
