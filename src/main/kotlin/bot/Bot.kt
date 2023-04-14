package bot

import bot.commands.AightCommand
import bot.commands.BoiCommand
import bot.commands.BruhCommand
import bot.commands.EverydayCommand
import bot.commands.GimmeCommand
import bot.commands.HipCommand
import bot.commands.LofiCommand
import bot.commands.OkCommand
import bot.commands.OneCommand
import bot.commands.RewindCommand
import bot.commands.RockCommand
import bot.commands.SkincareCommand
import bot.commands.SlaveCommand
import bot.commands.YeetCommand
import bot.configurations.configurations
import bot.listeners.MessageListener
import bot.listeners.VoiceLeaveListener
import com.jagrosh.jdautilities.command.CommandClientBuilder
import com.jagrosh.jdautilities.commons.waiter.EventWaiter
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
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
                /* Command to play a track */
                OneCommand(),

                /* Command to skip the current track */
                OkCommand(),

                /* Command to play One Ok Rock songs */
                RockCommand(),

                /* Command to play the lo-fi stream */
                LofiCommand(),

                /* Command to clear the queue and play No Scared */
                GimmeCommand(),

                /* Clears the queue and plays Hip To Be Square */
                HipCommand(),

                /* Command that loops the current track */
                RewindCommand(),

                /* Command to clear the queue */
                YeetCommand(),

                /* Command to shuffle the queue */
                EverydayCommand(),

                /* Command to leave the voice channel */
                AightCommand(),

                /* Command to get the bot to reply "Bruh" back to you */
                BruhCommand(),

                /* Command to react boi to a message */
                BoiCommand(),

                /* Command to tag the other music bots if present */
                SlaveCommand(),

                /* Command to get the bot to reply with Patrick Bateman's skincare routine */
                SkincareCommand()
            )
        }

        /* Builder setup */
        with(builder) {
            addEventListeners(waiter, client.build(), MessageListener(), VoiceLeaveListener())
            setChunkingFilter(ChunkingFilter.ALL)
            enableIntents(GatewayIntent.GUILD_MEMBERS)
            setActivity(Activity.listening("!huh"))
            build()
        }
    }
}
