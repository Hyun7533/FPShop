move1 = setInterval(() => {
    document.querySelector('.container').style.transform = 'translate(-100vw)'
}, 3000);

move2 = setInterval(() => {
    clearInterval(move1)
    document.querySelector('.container').style.transform = 'translate(-200vw)'
}, 9000);

move3 = setInterval(() => {
    clearInterval(move2)
    document.querySelector('.container').style.transform = 'translate(0vw)'
    return move1;
}, 12000);