/*
 * ----------------------------------------------------------------------------------------------+
 *   * Group Leader: Daniel Hope
 *   * Member(s):
 *   *     - Georgina Luce
 *   *     - Nathaniel Primo
 *   *     - Michael Marc
 *   * Group #: 1
 *   * Filename: web.res.script.game.js-playermenu.js
 *   * Assignment: Final Exam
 *   * Creation Date:
 *   * Last Modified: 2017/12/27
 *   * Java Version: 1.8.0_141
 *   * Description: The standard functionality for the game
 * ----------------------------------------------------------------------------------------------+
 */

this.user = {

    username: "",
    score: 0,
    credit: 0
}

function init(user, score, credit) {

    this.user.username = user;
    this.user.score = score;
    this.user.credit = credit;
}

window.addEventListener("load", function () {

    this.audio = document.getElementById("game-music");

    this.score = document.getElementById("score-display");

    this.credits = document.getElementById("credit-display");

    this.scoreContainer = document.getElementById("score-container");

    this.visualDisplay = document.getElementById("visual-display");

    this.credits.innerText = this.user.credit.toFixed(2);;
    this.score.innerText = this.user.score.toFixed(2);;

    this.carGame = new CarGame();

    document.getElementById("music-control").addEventListener("click", function () {

        if (audio.paused) {

            audio.play();
            return;
        }
        audio.pause();
    });

    document.getElementById("view-score").addEventListener("click", function () {

        let isHidden = scoreContainer.style.visibility == "hidden" || scoreContainer.style.visibility == "";

        scoreContainer.style.visibility = isHidden ? "visible" : "hidden";
    });

    document.getElementById("credit-refill").addEventListener("click", function () {

        if (user.credit >= 100) {

            return;
        }
        user.credit = Math.min(user.credit + 50, 99);
        credits.innerText = user.credit.toFixed(2);
    });
});

function CarGame() {

    driverName = "YourPrefferedCar";

    var gameStarted = false;

    var gameOver = false;

    var playButton = document.getElementById("app-play");

    var carGame = new CarTest();

    carGame.theWinner = function (winner) {

        if (winner == driverName) {

            user.score += 50;
            score.innerText = user.score;

            alert('Congratulations, yon won');
        } else {

            alert('You lose, try again');
        }

        let xhr = new XMLHttpRequest();

        xhr.open("GET", "./write?login='" + user.username + "'&score=" + user.score + "&credit=" + user.credit);
        xhr.send();

        playButton.innerText = "Play Again";
        gameOver = true;
        gameStarted = false;
    }

    setInterval(function () {

        if (carGame.isSleeping || !gameStarted) {

            return;
        }

        this.user.credit -= .50;
        credits.innerText = this.user.credit.toFixed(2);

        if (this.user.credit < 0.5) {

            carGame.isSleeping = true;
            playButton.innerText = "Play";

            alert('You are out of credits, please add more by clicking the credits refill button.');
        }
    }, 1000);

    playButton.addEventListener("click", function () {

            if (user.credit < 0.5) {

                alert('You are out of credits, please add more by clicking the credits refill button.');
                return;
            }

            if (!gameStarted || gameOver) {

                visualDisplay.innerText = "";

                carGame.UseMyCar(driverName, function (msg) {

                    if (msg == undefined) {

                        visualDisplay.innerText += "\r\n";
                        return;
                    }
                    visualDisplay.innerHTML += msg;
                });

                gameOver = false;
                gameStarted = true;
            }
            else {

                carGame.isSleeping = !carGame.isSleeping;
            }

            playButton.innerText = carGame.isSleeping ? "Play" : "Pause";
        }
    );
}