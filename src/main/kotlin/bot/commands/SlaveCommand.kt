package bot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent

/**
 * Class that represents !slave command
 *
 * Tags other music bots if present
 */
class SlaveCommand : Command() {
    init {
        name = "slave"
        help = "Tags Rhythm Bot or other music bots if present"
    }

    override fun execute(event: CommandEvent) {
        val musicBotIDs = setOf("234395307759108106", "235088799074484224", "155149108183695360")
        val taggedMembers = ArrayList<String>()

        for (member in event.guild.members) {
            if (member.id in musicBotIDs) {
                taggedMembers.add(member.id)
            }
        }

        if (taggedMembers.isEmpty()) {
            event.reply("No slaves to be found")
        } else {
            event.reply(taggedMembers.joinToString(" ") { "<@$it>" })
        }
    }
}
