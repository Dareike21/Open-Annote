<link rel="shortcut icon" type="image/x-icon" href="logo_large.ico">
### Group Members

- Derek Hinojosa
	- Classification: Junior 
	- Major: Computer Science

- Maxwell Hosler
	- Classification: Junior
	- Major: Math
 
- Corey Ulman
	- Classification: Junior
	- Major: Computer Science, English

### Open Annote

<img src="assets/logo_large.png" alt="The Open Annote logo. It looks like a black italicized O with an a superscript in the center of a cream circle." />

Open Annote is an application that allows one to read and create annotated documents, similar to Genius Lyrics, Hypothes.is, and traditional annotated texts such as _The Annotated Shakespeare._ Open Annote is intended to be user-friendly, with GUI design being a major focus during development. 

### Application Features

- Opens and displays text files alongside annotation files 
- Creates annotations
- Loads multiple annotation files on one text file 
- Exports annotation and document files for sharing 
- Document editor lets one "import" any text to be used within Open Annote 
- Unique, functional filetype 

### Design Features

- Distinct, colorblindness-friendly colors are used, allowing the application to remain usable for as many users as possible. 

- The text is highly readable, with a large font and a clean background. Our reader follows norms for other text displaying applications (such as a browser's PDF reader), centering the text and darkening the blank space around it. Annotations are shown one at a time in a separate sidebar, which allows for more readable text and prevents too many annotations from cluttering the screen. 

- The program is responsive, minimizing frustration and confusion. Documents load quickly, buttons look visually distinct when pressed, and there is clear indication that feedback has been recieved (for example, when an annotation has been saved). 

- In order to switch between modes, tabs are included at the top of the page. The "tab" aeshtetic evokes web browsers and establishes that the modes have different functionality. In addition, the "read" and "annotate" tabs are similar for quicker habituation and learning. To avoid mode errors, we made the modes distinct (with varying colors for highlighting annotations, different menu buttons, and separate document loading). In addition, the modes' differing functionality means that a possible damage a mode error could do is minimal. 

- In order to make long texts more readable and minimize memory issues, documents are separated into chapters. One switches between chapters using buttons that are only clickable when there _is_ a next chapter. The current chapter is shown near the page turn buttons, allowing it to be unobtrusive but easily visible. When reading a document, one scrolls within a chapter and uses buttons to change chapters. 

- The multiple menus are consistenly named across modes and are named to minimize confusion. For example, our "Clear" menu button technically creates a new annotation set - but, to a user, it looks like it just clears the current annotation set, so that's how we named it! 

- The document creator has separate buttons for going to the next chapter and adding a new chapter. This allows the user to see how many chapters are in the document they're creating, and lessens the chance of one accidentally creating many extra chapters. 

<!---(This website uses markdown btw)

```markdown
Syntax highlighted code block

# Header 1
## Header 2
### Header 3

- Bulleted
- List

1. Numbered
2. List

**Bold** and _Italic_ and `Code` text

[Link](url) and ![Image](src)
```
--->

### Implementation 

This program was written in Java, with Java Swing and AWT used to build the GUI. No external packages were used. 

### Github Repository

[Hosted on Github](https://github.com/Wooster-CS320-UIDesign/open-annote)
