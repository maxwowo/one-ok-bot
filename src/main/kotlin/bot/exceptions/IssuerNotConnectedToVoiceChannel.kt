package bot.exceptions

import net.dv8tion.jda.api.entities.User

class IssuerNotConnectedToVoiceChannel(issuer: User) : Exception(issuer.id)
