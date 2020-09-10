const buttonSelect = document.querySelectorAll('[data-selection]');/*sets the button sel variable to be whatever images
it is saved as */

const fCol = document.querySelector('[data-fcol]');//variable used to set the column correctly for win/lose case
const compScore = document.querySelector('[data-comp-points]');//variable used for comp score
const userScore = document.querySelector('[data-user-points]');//variable used for user score


const SELECTIONS = [//GLOBAL VARIABLE OF ALL POSSIBLE WIN COMBINATIONS
    {name: 'r', statement: "Rock", beats: 's'},
    {name: 'p', statement: "Paper", beats: 'r'},
    {name: 's', statement: "Scissors", beats: 'p'},
]

function compChoose(){/*function that randomizes the choice of the computer using rand num gen. Done to randomize array index*/
    const rand = Math.floor(Math.random()* SELECTIONS.length);/*makes a variable in which the floor of the number of items in the selection global variable is a num
    from 0-2*/
    return SELECTIONS[rand];//returns the index of the rand var. i.e. returns item at index 0,1,2.
}

function choose(choice){/*function that indicates which choice has been made by user*/

    const compSel = compChoose();//sets the computers choice here
    const youWin = wins(choice, compSel);//checks if you won
    const compWin = wins(compSel, choice);//checks if computer won

    display(compSel, compWin);//displays computer results.
    display(choice,youWin);//displays your results
    if (youWin)
    {
        addPoints(userScore);
    }
    if (compWin)
    {
        addPoints(compScore);
    }

    //console.log(compSel);//just used to display. used for testing
}

function wins(userChoice, compChoice){/*states that you win if conditions have been met*/
    return userChoice.beats === compChoice.name;//returns bool if both value and data type of the user beats option is the same as the comp choice name option

}

function addPoints(score){
    score.innerText = parseInt(score.innerText) +1 ;//converts text to int then adds points
}

function display(selection, wins){/*function that will display selections to the screen*/
    const div = document.createElement('div')
    div.innerText = selection.statement;//makes the created variable equal to the given name in my array
    div.classList.add('final-sel');// creates the final-sel class in the style.css

    if (wins)
    {
        div.classList.add('wins');//creates the final-sel.wins class in the style.css
    }

    fCol.after(div);
}


buttonSelect.forEach(buttonSelect => {/*runs for each individual button*/
    buttonSelect.addEventListener('click', e => {//adds event for button click
        const selB = buttonSelect.dataset.selection; /*will retrieve the value of the button (r,p,s)*/
        const selection = SELECTIONS.find(selection => selection.name === selB )//loops through to find out what button the user has selected from the array
        choose(selection);/*function call here. for user and comp choice*/
    })
})

