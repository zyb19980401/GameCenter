## Instruction

#### Set up the GameCentre
- Check out from version control using: https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0626
- After cloning and pulling, click [File] -> [Open] : group_0626 -> Phase 2 -> GameCentre, select GameCentre and click ok.
- Let the Gradle build, click [add to root] if there is an [unregistered root detected] popped up at the bottom right of the screen.
- You should then be able to run our app using your virtual device (you would have to run it twice if it was your first time running it).

#### Sign up and Login in
- Firstly, you will see the login in page. You need to sign up an user account with specific username and password in numbers only.
  You can sign up many user accounts. All users accounts will be stored in user account manager by serialization.
- After signing up, you can login in. If username and corresponding password is matched, you will proceed to the next page.
  The information of saved game and scoreboard will be loaded if you successfully log in.

#### Choose the game in game centre
- The game centre will displays several games. Now we have three games for your choice.
  Click on each respective image, and you will go to the respective starting page.

#### Siding Tile Game
##### How to start
- Load saved game/ Start new game
  If you are a new user, you don't have any saved file, so the load game button is disabled. You can only start the new game.
  If you have played the game before, the load game button is enabled, and you can continue to play.
  - Setting
  In setting, you can choose the game complexity(3x3,4x4,5x5) and expected undo steps(1-5 steps)
  You can also upload your own image to be the game background：To put photos from your computer to your
  virtual device, first open your virtual device. You can find an app called photos, open it. You can drag
   pictures from your local file to photos. Then open our app GameCentre and click setting button.
   First click upload picture button, and select a picture from your gallery. Your selected image will be displayed below the button. You will find
  the game background has been changed once you open a new game. You can delete this self-defined background or upload
  another image in setting again.
  - Scoreboard
  There are two scoreboards. 
  The first one is the game scoreboard, displaying the top 10 scores among all users.
  The second one is the user scoreboard, records the history of the login user's scores.
  

##### Playing the game
- Gameboard
  Once you click the new game, at the top, there is a timer and a recorder to record steps.
  Your final score will be calculated based on the time, steps you used to finish the game,
  your total undo steps and game complexity.
  The formula is: final score = 500 -0.15 * mTimeLeftInMillis / 1000 - 0.25 * board.numStep - 3 * numUndo - 10 * complexity, rounded to an integer.
- AutoSave
  The game status will be automatically saved for each time you swap a tile.
- Undo
  At the top, there is also an undo button. If in setting you choose to undo 3 steps, you can only undo 3 steps at most.
- Puzzle solved
  Once you successfully solve the puzzle, you can see a congratulations page and your score.
  Your score will be automatically stored in the scoreboard.
  And the load game button is disabled once you solve a game. You can start a new game then.

#### AirCraft Game
The second game is an Aircraft shooting game.

##### How to start
- SETTING
  In setting, you can choose the game difficulty(easy or hard) with default value easy, and the bullet type(brown one and the blue one) with the default value brown,
  if you choose hard, the speed of enemy crafts and enemy bullets moving is faster than that of easy mode, and if you choose blue bullet, the image of bullet shot by AirCrafts will change correspondingly.

##### Playing the game
- START
  start a new game with complexity chosen(easy or hard) and bullet image chosen, shoot as many enemy crafts as you can and try to avoid being hit by the enemy craft and enemy bullets which is moving downwards
  and if you are hit by two enemy crafts or bullets, game is over and you will see a GameOver page with number of enemy killed, time survived, and points you got on that page.
  If you exit before your aircraft is killed, the game is still over and the GameOver page will pop out. Your game will not be saved, as this is a survival game.
- Personal ScoreBoard
  Your personal 10 highest scores will be shown on this page with highest on the top, decreasing downwards. Empty if there aren't enough scores.
- Top 10 ScoreBoard
  The scoreboard for top 10 plays among all the players, each contains corresponding username and score. Empty if there aren't enough scores.


#### 2048 Game
We have included the classic 2048 game as the third game on our Game Centre.
In addition to the original game, we have included Restart and Undo features, while consecutive undo is prohibited.
You can choose to play with Pokemon theme by selecting it at the drop down menu on the Starting Activity screen.
Note that your selection won't be saved, so if you would like to continue with the Pokemon theme, you would have to select again before loading your game.

##### How to start
- Load game and Scoreboard features are same as the Sliding Tiles.
- Each time you press a New Game, a new game board with two random number tiles is generated.
We have programmed in a way that the probability of ID 2, 4 tiles added is 0.85 to 0.15.

##### Playing the game
- You proceed with the game by clicking the action buttons: left, right, up, and down.
A Toast is made if your move is invalid. You can undo as many times as you want before Game Over has reached.
However, you can only undo at most every other step.
- Your score is recorded automatically once no possible valid moves could be made and you would have reached Game Over. Undo is then disabled.
- A Toast will be made and you can only restart or press the back key.

#### Logout
Click on the logout button, or the back button on the Game Centre page, all your login information will be cleared.
And you can use another user account to play the games.