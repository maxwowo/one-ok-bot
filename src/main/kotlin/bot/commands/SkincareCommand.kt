package bot.commands

import com.jagrosh.jdautilities.command.Command
import com.jagrosh.jdautilities.command.CommandEvent
import net.dv8tion.jda.api.EmbedBuilder

/**
 * Class that represents !skincare command
 *
 * Replies with Patrick Bateman's skincare routine
 */
class SkincareCommand : Command() {
    init {
        name = "skincare"
        help = "Offers you some helpful skincare tips"
    }

    override fun execute(event: CommandEvent) {
        val builder = EmbedBuilder()

        builder.setDescription(
            "I live in the American Gardens Building on W. 81st Street on the 11th floor. My name is Patrick " +
                "Bateman. I’m 27 years old. I believe in taking care of myself and a balanced diet and rigorous " +
                "exercise routine. In the morning if my face is a little puffy I’ll put on an ice pack while doing " +
                "stomach crunches. I can do 1000 now. After I remove the ice pack I use a deep pore cleanser lotion. " +
                "In the shower I use a water activated gel cleanser, then a honey almond body scrub, and on the face " +
                "an exfoliating gel scrub. Then I apply an herb-mint facial mask which I leave on for 10 minutes " +
                "while I prepare the rest of my routine. I always use an after shave lotion with little or no " +
                "alcohol, because alcohol dries your face out and makes you look older. Then moisturizer, then an " +
                "anti-aging eye balm followed by a final moisturizing protective lotion."
        )

        event.reply(builder.build())
    }
}
