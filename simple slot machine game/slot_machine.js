const prompt = require("prompt-sync") ();

const NUMBER_OF_ROWS = 3;
const NUMBER_OF_COLS = 3;

const SYMBOL_COUNT = {
    S: 1,
    A: 2,
    B: 3,
    C: 4,
    D: 5
};

const SYMBOL_VALUE = {
    S: 10,
    A: 7,
    B: 5,
    C: 3,
    D: 1
};

const deposit = () => {
    while (true) {
        const depositAmount = prompt("Enter deposite amount: ");
        const numberDepositAmount = parseFloat(depositAmount);

        if (isNaN(numberDepositAmount) || numberDepositAmount <= 0) {
            console.log("Invalid input!!!, Try Aaain");
        } else {
            return numberDepositAmount;
        }
    }
};

const getNumberOfLine = () => {
    while (true) {
        const lines = prompt("Enter the number of line to be bet on (1-3): ");
        const numberOfLine = parseFloat(lines);

        if (isNaN(numberOfLine) || numberOfLine <= 0 || numberOfLine > 3) {
            console.log("Invalid input!!!, Try Aaain");
        } else {
            return numberOfLine;
        }
    }
};

const getBet = (balance, line) => {
    while (true) {
        const bet = prompt("Enter the bet per line: ");
        const numberOfbet = parseFloat(bet);

        if (isNaN(numberOfbet) || numberOfbet <= 0 || numberOfbet > balance / line) {
            console.log("Invalid input!!!, Try Aaain");
        } else {
            return numberOfbet;
        }
    }
};

const spin = () => {
    const symbols = [];
    
    for (const[symbol, count] of Object.entries(SYMBOL_COUNT)) {
        for (let i = 0; i < count; i++) {
            symbols.push(symbol);
        }
    }
    
    const reels = [];
    
    for (let i = 0; i < NUMBER_OF_COLS; i++) {
        reels.push([]);
        const reelsSymbol = [...symbols];
        
        for (let j = 0; j < NUMBER_OF_ROWS; j++) {
            const randomIndex = Math.floor(Math.random() * reelsSymbol.length);
            const selectedSymbol = reelsSymbol[randomIndex];
            reels[i].push(selectedSymbol);
            reelsSymbol.splice(randomIndex, 1);
        } 
    }
    return reels;
};

const transpose = (reels) => {
    const row = [];
    
    for (let i = 0; i < NUMBER_OF_ROWS; i++) {
        row.push([]);
        
        for (let j = 0; j < NUMBER_OF_COLS; j++) {
            row[i].push(reels[j][i]);
        }
    }
    return row;
};

const printRow = (rows) => {
    for (const row of rows) {
        rowSrring = "";
        
        for (const [i, symbol] of row.entries()) {
            rowSrring += symbol;
            
            if (i != row.length - 1) {
                rowSrring += " | ";
            }
        }
        console.log(rowSrring);
    }
};

const getWin = (rows, bet, line) => {
    let winCount = 0;
    
    for (let row = 0; row < line; row++) {
        const symbols = rows[row];
        let allSame = true;
        
        for (const symbol of symbols) {
            if (symbol != symbols[0]) {
                allSame = false;
                break;
            }
        }
        
        if (allSame) {
            winCount += bet * SYMBOL_VALUE[symbols[0]];
        }
    }
    return winCount;
};

const game = () => {
    let balance = deposit();
    
    while (true) {
        console.log("Your Balance : " + balance);
        
        const numberOfLine = getNumberOfLine();
        const bet = getBet(balance, numberOfLine);
        balance -= bet * numberOfLine;
        
        const reels = spin();
        const row = transpose(reels);
        printRow(row);
        
        const win = getWin(row, bet, numberOfLine);
        console.log("Win Amount: " + win); // Debugging statement
        balance += win;
        console.log("Updated Balance: " + balance); // Debugging statement
        console.log("You won " + win.toString());
        
        if (balance <= 0) {
            console.log("You ran out of money!!!");
            break;
        }
        const playAgain = prompt("Play again? (y/n): ")
        
        if (playAgain != "y") break;
    } 
}


game();