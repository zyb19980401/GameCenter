#### Contact information
• Congyun Jin\
jarina.jin@mail.utoronto.ca\
6479969802

• Guoqing Chen\
guoqing.chen@mail.utoronto.ca\
6479395879

• Yuanbo Zhang\
yuanbo.zhang@mail.utoronto.ca
7788821008

• Jiaxuan Chen\
luke.chen@mail.utoronto.ca
4168327590

• Runting Yang\
runting.yang@mail.utoronto.ca
4373454632

#### Communication Tools
WeChat

#### Team Contract
◦I will get my allocted work done on time.
◦I will attend every team meeting and actively participate.
◦Should an emergency arise that prevents me from attending a team meeting, I will notify my team immediately.
◦The work will be divided roughly equally among all team members.
◦I will help my team to understand every concept in the project.
◦If I do not understand a concept or code, I will immediately ask my team for help.

#### Work Style
We discuss firstly, and do our own part individually. Once we get some progress or meet some problems,
we will have a meeting and figure out together. The last few days we will have meeting every day to debug
and test our code.

### Meeting Minutes

11.11\
first group meeting at Lab\
assigned individual tasks\
Congyun Jin & Guoqing  Chen: Game Tic-Tac-Toe(changed to 2048 later on)\
Yuanbo Zhang & Runting Yang: Game Aircraft\
Jiaxuan Chen: Solvable and Unittest\

11.14\
finished git set-up and all members managed to pull from git
finished creating solvable board
first draft of the game Aircraft, still had a lot of problems like insufficient bullets, animation
speed and setting ... 

11.16\
Accidently deleted the whole repository of our phase2 when we wanted to clean the repository...

11.20\
fixed the repository by instructor 
Yuanbo Zhang pushed the lastest version of our phase2 


11.22\
added 2048 game (unfinished);
adjusted imageview at gamecentre
moved userscoreboard into slidingtiles
Add aircraft setting to change bullet style and difficulty;
Add a few layout for game Aircraft
Change SkyManager to non-static

arrange classes and add corresponding classes to each game package(Aircraft, SlidingTile
and two-zero-four-eight)


11.23\
write a few UnitTest for SlidingTile
Extract GameActivityController and SlidingTilesFileSaver from GameActivity.
fixed restart and undo button of 2048 game.

11.24\
updated on undo(still has bug) and restart of game 2048; changed app name
add tests for UserAccount, ScoreTuple.and Session
problems remian: how to write test for controller and how to mock context.


11.25\
fixed invalid move and restart. undo has bug
Fixed a bug that cannot load a new aircraft game.
able to save score upon GameOver

11.26\
remove UserscoreActivity outside from slidingtile package
finalize 2048 game 
password can be masked
create blank_tile for touch move when upload image from gallery.
fixed score table bug
add complexity to game Aircraft, still need to write the javadoc.

11.27\
fixed zerotilelist bug
construct super for tile
change px from 30 to 25 which is used to determine whether two flyable objects hit each other.
problems remain like version conflict.

11.28\
pushed the lastest complete version of phase2
add the saver file
add controller to each activity file 
use singleton in UserAccountManager

11.29, 11.30\
fix problems caused by separating controllers from activity for both slidingTiles and 2048.
added README.md
modify and add a few AirCraft and 2048 unit tests.
add javadoc and citation for AirCraft class.
pushed the final version.


