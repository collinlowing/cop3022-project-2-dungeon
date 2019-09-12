# Project 2 - The Dungeon

## Project Outcomes - Develop a Java program that:
* Creates user designed classes, following a UML specification.
* Uses proper design techniques.
* Reads input from the keyboard using a Scanner Object and its methods.
* Uses command line arguments to dynamically define a problem space.
* Generates non-trivial CLI output formats.

## Prep Readings:
Zybooks chapter 1 - 7
### Background Information:
In this project, we will develop a simple text-based "maze crawler" in the fashion of what's now called "Abandonware", i.e. software that once was a huge hit and now is rightfully (?) abandoned, just like "Rogue":
* https://www.myabandonware.com/game/rogue-4n/play-4n
* Another example is (of course!)
	* The almost classic "Colossal Cave"
		* https://www.amc.com/shows/halt-and-catch-fire/colossal-cave-adventure/landing
		* Hint: To get by the snake, you better have the wicker cage! Birds can be fierce!

## Project Requirements:
1. Your solution shall accept up to twenty rooms as command-line arguments which then create the dungeon.
1. The input format can be assumed to exactly match the specification given below (specifically, 7 fields of data per room).
1. A user must be able to view the menu options via a help command.
1. A user must be able to perform the actions listed in the [sample run](#sample-run) below. Invalid options shall notify the user of the command failure and prompt the user for an option again.

## Room Specification
Rooms will be given as command line arguments when running your program for the purpose of grading.
The format will be as shown below, seven fields for each room, for no more than 20 rooms.
A `-1` value for an index_of_adjacent_room_to_the_X mean that no portal exists in that direction, only a solid wall.
Also note that the quotes are important otherwise the room names and descriptions with spaces would get broken into multiple `String`s.
```
"Name of the room" "Description of the room" "item_in_room:value" index_of_room_to_the_north index_of_room_to_the_east index_of_room_to_the_south index_of_room_to_the_west
```

For example, a couple of rooms might look as follows:
```
"The Entrance" "You've entered into an odorous dungeon" "lantern:20" 1 -1 -1 2
"The Brain Cellar" "Skulls of past victims lie spread about in the room" "" -1 0 -1 -1
```

## Sample run
<pre><code>
Command (h for help): <b>h</b>
h(elp) - print this text
l(ook) - show current location
i(nv) - show current item
g(o) direction - go into direction N, E, S or W
d(rop) item - drop item
t(ake) item - take item
q(uit) - quit game
Command (h for help): <b>l</b>
You're in a dark large room
+---------------------+
|                     |
|                     |
|
|                     |
|                     |
+---------------------+
bronze_dagger value: 5

Command (h for help): <b>t dagger</b>
ERROR: Cannot find dagger
Command (h for help): <b>t bronze_dagger</b>
Command (h for help): <b>i</b>
bronze_dagger value: 5
Command (h for help): <b>g E</b>
Command (h for help): <b>l</b>
You're in a large dark room
+---------- ----------+
|                     |
|                     |
                      |
|                     |
|                     |
+---------------------+

Command (h for help): <b>go N</b>
Command (h for help): <b>l</b>
You're in a dark large room
+---------------------+
|                     |
|                     |
|
|                     |
|                     |
+---------------------+

Command (h for help): <b>g E</b>
Command (h for help): <b>look</b>
You're in a large dark room
+---------- ----------+
|                     |
|                     |
                      |
|                     |
|                     |
+---------------------+

Command (h for help): <b>g W</b>
Command (h for help): <b>l</b>
You're in a large room, very dark
+---------------------+
|                     |
|                     |
                      |
|                     |
|                     |
+---------- ----------+
the_grail value: 100000

Command (h for help): <b>take tHe_GrAiL</b>
You're rich! Go forth and do great things.
</code></pre>

## Classes
* You'll need a main driver class that controls gameplay, in addition to the following classes for storing relevant data.

### `Directions` - recommended for readability, but not required
![UML of Directions class](http://www.plantuml.com/plantuml/png/SoWkIImgAStDuSh8J4bLICqjAAbKo4tDJKejAkPApaaiBbPmoInAJIx9pCyhvghcqbQe1keeoKokLV0BzNLo3OhtTujtTWnoyVHpz8btyGnoTGxnzFSBjbB8p2j1KUqV5EA18Uhg61o28HZi7uf5QRWhJ6ajbqDgNWhGpG00)
* Simple class to make code more readable. Without it, you'd have things such as `adjacency[2]` and that is much less clear that `adjacency[Directions.SOUTH]`.

### `Player`
![UML of Player class](http://www.plantuml.com/plantuml/png/PO-nYiCm341tVON719CVo1IuvGB7KRfqTcFH62oho4eWbFvxxQOKIXTXtjF3QCZgH1UAC0UF1kcgco6aCqg6ARgSpI6w3GLkq0L5-iNeUpEM5tJJ8e99_tXo6ZZrviXC07OlcXRi1VLdNwggQLyhDd_oEefREVXdCwQrA9Qj5FjVbVwZdbXqhZ0a1UkyhxAfuvqB4g_unUymOFBbq0S0)
* Member data
	* `itemHeld` stores the current item held by the player, or `null` if no item is held
	* `currentLocation` stores the current room that the player is in.
* Methods
	* `getCurrentRoom()` - returns the current room the player is in
	* `setCurrentRoom(Room)` - Used at the start of the game to set the player's location and gets updated on each move.
	* `getInventory()` - Returns the current item held or an empty string if no item is held.
	* `getNetWorth()` - Returns the integer value of the item held by the player, 0 if no item is held.
	* `addItem(Item)` - Sets the `itemHeld` to the new item.
	* `removeItem(Item)` - Drops the item, if held.

### `Room`
![UML of Room class](http://www.plantuml.com/plantuml/png/PP2_Ii103CRtF4N6AsqtfquLN5nr58Vp4lHAaojtiI3YkzjGiO9B_dmalozaRXQhlSiARvaOMJRxG65vuTeWhR4rV2X5u1D6ZS8JFbhD-WeZSKirRvQBNc8gQgpM9hmt5XWZBQp9d71edfvkdg7t8fmxwDG1Vve_q0xwIEIOuA6RS2-PeAyiPUV_UcExfIKcLWjtkN9ooE2UruoLqoTq-7lp5yoiT3pW6m00)
* Member data
	* `name`
	* `description` - the text to show to the user.
	* `contents` - The item that is in the room, or null if no item is present
	* `adjacentRooms` - An array that holds the four adjacent rooms. It should be null if no room is adjacent in a given direction.
* Methods
	* `Room(String name, String description)` - Constructor that creates a room.
	* `addItem(Item)` - Places an item in the room.
	* `removeItem(Item)` - Removes an item from the room, if that item is in the room, alerts the user if it does not.
	* `setAdjacent(Direction, Room)` - Creates a portal from this room to the room passed in. That portal is in the Direction given.
	* `toString()` - Prints out a room with description, any item that is present in the room, and the four walls with gaps representing any open portals, e.g. the room below has a portal to the west, as well as one to the south, and contains the_grail.
		* The parts of the printout below are best implemented in separate helper methods such as `getDescription()`, `getTop()`, etc.
	```
	You're in a large room, very dark
	+---------------------+
	|                     |
	|                     |
						  |
	|                     |
	|                     |
	+---------- ----------+
	the_grail value: 100000
	```


### `Item`
![UML of Item class](http://www.plantuml.com/plantuml/png/LOp12i8m38RlUOeSD-fUOASTl7WMlDSrp43JZIOLH7ntgHLqjv_l-v99pMSh4k76WP1aimSAoPMomXozAfwC19um1D8vysQyfX7FbZajCDnzB3GY9mDNmwu9_8iFrU8dxC4jP9SwiUkntLLq_ELDVFyxMzlQqHTCb4AH-0O0)
* Member data
	* `description` - States what the item is called.
	* `value` - The value of the item in integer currency units (I sound like Beldar Conehead).
* Methods
	* Constructor - must accept a name and value for the item to create.
	* Simple getters.
	* `toString()` - this common method name is used to display the complete details of an object instance.

## Programming Strategy
* To pass command line arguments into a Java program in Eclipse, click _Run_ in the top menu, then click _Run configurations..._.
In the panel that opens, there are tabs along the top on the right side, click the one that says _(x)= Arguments_.
Then, paste the maze specification below into the _Program arguments_ textarea.
```
"Chamber of Secrets" "You're in a large dark room" "" 1 -1 -1 2 "Hall of the Mountain King" "You're in a dark large room" "bronze_dagger:5" -1 0 -1 -1 "The Vault" "You're in a large room, very dark" "the_grail:100000" -1 -1 0 1
```
* The chunks of the above will be 21 Strings in the args array for your `main()` method.
		* As a quick test of whether you are using it correctly, simply iterate over `args` and print to screen.
* Look at the classes that you need to build and determine dependencies. Start by building a class that doesn't depend on any other classes and extend from there.
	* Ideally, do this by solidly testing your classes as you go, JUnit is very easy to use and can allow you to easily test partially complete classes, one method at a time.

