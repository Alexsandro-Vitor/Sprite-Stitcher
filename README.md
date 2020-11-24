# Sprite-Stitcher
Character Spritesheet Generator, designed for usage with any spritesheet style.

## How to use
1. Choose the spritesheet size and the zoom of the visualization screen. You can also choose a template from a Templates.tsv file.
2. Choose the template or manually insert the values and click "Continue".
3. Two windows will open: the left one has all the options, while the right one shows the spritesheet.
4. Choose the piece you want for each layer. At Layer Order you can see the overlaying order of the layers.
5. When you finish, type the name of the spritesheet and click "Save".
6. The spritesheets are saved at ``Assets/[spritesheet_style_name]/sprites``.

## Layer Order

*Sorted from back to front:*
1. Back
2. Body
3. Eyes
4. Legs B
5. Torso B
6. Hands
7. Shoes
8. Legs A
9. Torso A
10. Face
11. Back
12. Hair
13. Helm

## Advanced Usage

### Character randomization

* Clicking "Random Parts" at the bottom right of the window will randomize all parts of the character.
* Checking "Lock" at the row of any part prevents it from being randomized when randomizing the parts. This can be used to quickly create diverse spritesheets with some control, such as a group of people in uniforms, for example.

### Color Change: RGB/HSL

* Any grayscale color (R == G == B) in the sprite part will be filtered by the color defined in the spinners.
* By changing the Hue Swap value, it is possible to also change the color of colored parts by incrementing their Hue value.
* The color can be chosen in either RGB ou HSL.
* Changing Alpha is also possible in this mode, creating translucid parts.
* Click the "Random" button at the row of a specific part to pick a random color for its filtering.

### Color Change: Palette

* Set an Original palette to choose the color of the part which you want to change.
* Set the New palette with the colors you want to replace the original ones with.
* The colors are replaced following their ordr in the palettes: 1st original color is replaced by 1st new color, 2nd original is replaced by 2nd new, and so on. If the new palette has less colors, it will only change the first N colors on the original palette.
* Changing Alpha is also possible in this mode, creating translucid parts.

### Licenses
* Any sprite part can have an ``.license`` file included with it. The ``.license`` file is a simple text file with the same name of the part and may contain information about the license for using said part.
* When a full spritesheet is generated, all the ``.license`` files are also assembled, generating a full ``.license`` file listing the parts used to generate the spritesheet and their licenses.

### Setting up a new spritesheet style folder

* After you choose your configs and click "Continue" (step 2 of How to Use), if the spritesheet style folder doesn't exist, it will be created will all the subfolders it needs inside the ``Assets`` folder. If the ``Assets`` folder doesn't exist, it will be created as well.
* You can then add the files you want to the folder and click "Update folders" to update the files in the tool.

### Setting up a new spritesheet template

* Spritesheets are defined at ``Assets/Templates.tsv``. If this file doesn't exist, just create it.
* Every line in the file is a template. The values to be defined are, in tis order:
	1. Name of the template (also the name of the folder for that spritesheet style).
	2. Width of the spritesheet
	3. Height of the spritesheet
	4. Back Y (the Y coordinate where the row at which the character is facing north starts)
	5. Back Height (the Y coordinate where the row at which the character is facing north ends)
	6. Row order (also shown in the template name, but not used for anything else)

	Ex.: ``Cabbit	72	128	0	32	NESW`` is the template for the [Cabbit](https://opengameart.org/content/cabbit-bases-collection) spritesheet style.