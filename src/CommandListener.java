import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Invite;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class CommandListener extends ListenerAdapter
{
	public void onGuildMessageReceived(GuildMessageReceivedEvent e)
	{
		String msg = e.getMessage().getContentRaw();
		
		if(msg.equalsIgnoreCase(Main.PREFIX + "invitetop"))
		{
			e.getGuild().retrieveInvites().queue((invlist) ->
			{
				EmbedBuilder eb = new EmbedBuilder();
				eb.setTitle("Invite stats:");
				eb.setDescription("```");
				for(Invite i : invlist)
				{
					eb.appendDescription("\n\n" + i.getUrl() + "\t" + "Uses: " + i.getUses());
				}
				eb.appendDescription("```");
				
				e.getChannel().sendMessage(eb.build()).queue();
			});
		}
		
		if(msg.equalsIgnoreCase(Main.PREFIX + "disableinvitetop"))
		{
			e.getChannel().sendMessage("*invitetop command disabled-- ask al if you want it back up*").queue();
			Main.jda.shutdownNow();
			System.exit(0);
		}
	}
	
	public boolean isStaff(Member m)
	{
		for(Role r : m.getRoles())
		{
			//admin: 602889336748507164  mod: 565626094917648386
			if(r.getId().equals("602889336748507164") || r.getId().equals("565626094917648386"))
				return true;
		}
		
		return false;
	}
}
