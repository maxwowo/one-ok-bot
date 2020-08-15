package bot.listeners

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class MessageListener : ListenerAdapter() {
    override fun onMessageReceived(event: MessageReceivedEvent) {
        val builder = EmbedBuilder()
        val content = event.message.contentRaw.toLowerCase()

        if (event.jda.selfUser in event.message.mentionedUsers) {
            if ("good bot" in content) {
                builder.setDescription("Damn right I am")
            } else if ("bad bot" in content) {
                builder.setDescription(javaClass.getResource("/copypastas/navy_seal.txt").readText())
            }
        }

        event.textChannel.sendMessage(builder.build()).queue()
    }
}
