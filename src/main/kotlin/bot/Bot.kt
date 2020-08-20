package bot

import bot.commands.AightCommand
import bot.commands.BruhCommand
import bot.commands.EverydayCommand
import bot.commands.LofiCommand
import bot.commands.OkCommand
import bot.commands.OneCommand
import bot.commands.RockCommand
import bot.commands.SkincareCommand
import bot.commands.SlaveCommand
import bot.commands.YeetCommand
import bot.commands.MoreCommand
import bot.configurations.configurations
import bot.listeners.MessageListener
import bot.listeners.VoiceLeaveListener
import com.jagrosh.jdautilities.command.CommandClientBuilder
import com.jagrosh.jdautilities.commons.waiter.EventWaiter
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.ChunkingFilter

/**
 * Class that represents the bot
 *
 * Initializes the various JDA factories and runs the bot
 */
class Bot : Runnable {
    override fun run() {
        val builder = JDABuilder.createDefault(configurations.token)
        val waiter = EventWaiter()
        val client = CommandClientBuilder()

        /* Client setup */
        with(client) {
            setOwnerId(configurations.owner)
            setHelpWord(configurations.helpCommand)
            setPrefix(configurations.prefix)

            addCommands(
                /* Command to get the bot to reply "Bruh" back to you */
                BruhCommand(),

                /* Command to play a track */
                OneCommand(),

                /* Command to skip the current track */
                OkCommand(),

                /* Command to play One Ok Rock songs */
                RockCommand(),

                /* Command to tag the other music bots if present */
                SlaveCommand(),

                /* Command to get the bot to reply with Patrick Bateman's skincare routine */
                SkincareCommand(),

                /* Command to clear the queue */
                YeetCommand(),

                /* Command to shuffle the queue */
                EverydayCommand(),

                /* Command to leave the voice channel */
                AightCommand(),

                /* Command to play the lo-fi stream */
                LofiCommand(),

                /* Command to clear the queue and play No Scared */
                MoreCommand()
            )
        }

        /* Builder setup */
        with(builder) {
            addEventListeners(waiter, client.build(), MessageListener(), VoiceLeaveListener())
            setChunkingFilter(ChunkingFilter.ALL)
            enableIntents(GatewayIntent.GUILD_MEMBERS)
            build()
        }
    }
}
