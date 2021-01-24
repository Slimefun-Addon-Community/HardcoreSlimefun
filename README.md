# :skull_and_crossbones:Hardcore - Slimefun

The point of this addon is to make Slimefun as hard as possible.<br>
I don't even know why you would want to install this, be nice to your players.


Still here? Okay, let me show you how to make your server true hardcore.
* [How to configure this plugin](#)

## :headphones: Discord Server
You can find Slimefun's community on Discord and connect with thousands of other members from the community.<br>
The Slimefun Addon Community also has its own discord server for any of our projects too!

<p align="center">
  <a href="https://discord.gg/slimefun">
    <img src="https://discordapp.com/api/guilds/565557184348422174/widget.png?style=banner3" alt="Discord Invite"/>
  </a>
  <a href="https://discord.gg/V2cJR9ADFU">
    <img src="https://discordapp.com/api/guilds/799294416196075572/widget.png?style=banner3" alt="Discord Invite"/>
  </a>
</p>

# :wrench: Setting up your hardcore server
Once you have this plugin installed, you will need to configure it.<br>
Navigate to `/plugins/HardcoreSlimefun/config.yml`, here you will be able to change the following settings:
```yaml
hardcore-settings:
  researches:
    ## Enabling this will make Players lose all their researches when they die
    reset-on-death: false
    
    ## You can make researches fail with a given chance, the player will still lose exp but not unlock the research(0-100)
    failure-chance: 0
```
