# :skull_and_crossbones:Hardcore - Slimefun

The point of this addon is to make Slimefun as hard as possible.<br>
I don't even know why you would want to install this, be nice to your players.


Still here? Okay, let me show you how to make your server true hardcore.
* [How to configure this plugin](#wrench-setting-up-your-hardcore-server)

## :headphones: Discord Server
You can find Slimefun's community on Discord and connect with thousands of other members from the community.<br>
The Slimefun Addon Community also has its own discord server for any of our projects too!

<p align="center">
  <a href="https://discord.gg/slimefun">
    <img src="https://discordapp.com/api/guilds/565557184348422174/widget.png?style=banner2" alt="Discord Invite"/>
  </a>
  <a href="https://discord.gg/SqD3gg5SAU">
    <img src="https://discordapp.com/api/guilds/809178621424041997/widget.png?style=banner2" alt="Discord Invite"/>
  </a>
</p>

# :wrench: Setting up your hardcore server
Once you have this plugin installed, you will need to configure it.<br>
Navigate to `/plugins/HardcoreSlimefun/config.yml`, here you will be able to change the following settings:

```yaml
# If the plugin should auto-update
auto-update: true
on-death:
  # If true, a player will lose a random research every death
  reset-random-research: true
  # Chance to reset all the player's researches on death. Percent scale of 0 to 100.
  # 100 will always fire and 0 will never fire.
  chance-to-reset-all-researches: 5

on-research:
  # Chance that researching can fail. Percent scale of 0 to 100.
  # 100 will always fire and 0 will never fire.
  chance-of-failure: 10

android:
  # Chance for the Android to malfunction. Percent scale of 0 to 100.
  # 100 will always fire and 0 will never fire.
  chance-to-malfunction: 10
  # Duration of the malfunction in seconds.
  malfunction-duration: 30

messages:
  lost-random-research: '&cYou lost a random research!'
  lost-all-research: '&cOh noes... You lost all your research!'
  research-failed: '&cResearch failed! You''ll need to do that again :^)'
  android-malfunctioned: '&cYour Android has malfunctioned! Let it cool down and start it again'
```
