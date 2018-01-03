/*
 *  ----------------------------------------------------------------------------------------------+
 *    * Group Leader: Daniel Hope
 *    * Member(s):
 *    *     - Georgina Luce
 *    *     - Nathaniel Primo
 *    *     - Michael Marc
 *    * Group #: 1
 *    * Filename: /web/res/script/game/js-playermenu.js
 *    * Assignment: Final Exam
 *    * Creation Date: 12/30/17 3:14 PM
 *    * Last Modified: 12/30/17 3:14 PM
 *    * Java Version: 1.8.0_141
 *    * Description: Handles the functionality of the game
 * ----------------------------------------------------------------------------------------------+
 */

this.user = {

    user: "",
    name: "",
    score: 0,
    credit: 0,
    mode: undefined,
    music: {

        isPlaying: false,
        position: 0
    },
    scoreVisible: false
}

function initUser(jsonString) {

    this.user = JSON.parse(jsonString);
}

function refreshUser(name, user, score, credit) {

    this.user.user = user ? user : this.user.user;
    this.user.name = name ? name : this.user.name;
    this.user.score = score ? score : this.user.score;
    this.user.credit = credit ? credit : this.user.credit;

    this.user.mode = document.getElementById("game-select").value;
    this.user.music.position = audio.currentTime;

    if (this.user.mode == 0) {

        sendRedirect("./cargame/cargame.jsp", JSON.stringify(this.user));
    } else if (this.user.mode == 1) {

        sendRedirect("./cardgame/cardgame.jsp", JSON.stringify(this.user));
    }
}

function sendRedirect(url, args) {

    let form = document.createElement("form");
    form.setAttribute("method", "POST");
    form.style.display = "none";

    let attributes = document.createElement("input");
    attributes.setAttribute("name", "env_att");
    attributes.setAttribute("value", args);

    form.appendChild(attributes);

    form.setAttribute("action", url);

    document.lastChild.appendChild(form);
    form.submit();
}

window.addEventListener("load", function () {

    this.audio = document.getElementById("game-music");

    this.score = document.getElementById("score-display");

    this.credits = document.getElementById("credit-display");

    this.scoreContainer = document.getElementById("score-container");

    this.visualDisplay = document.getElementById("visual-display");

    if (this.user.music.isPlaying) {

        audio.play();
        audio.currentTime = this.user.music.position;
    } else {

        audio.pause();
    }
    scoreContainer.style['visibility'] = user.scoreVisible ? "visible" : "hidden";
    this.credits.innerText = this.user.credit.toFixed(2);
    this.score.innerText = this.user.score.toFixed(2);

    if (this.user.mode) {

        if (this.user.mode == 0) {

            new CarGame();
        } else if (this.user.mode == 1) {

            new CardGame(function () {

                user.score += 50;
                score.innerText = user.score;

                let xhr = new XMLHttpRequest();

                xhr.open("GET", "../write?login='" + user.name + "'&score=" + user.score + "&credit=" + user.credit);
                xhr.send();
            });
        }

        document.getElementById("game-select").addEventListener("change", function () {

            this.user.mode = document.getElementById("game-select").value;
            this.user.music.position = audio.currentTime;

            if (this.user.mode == 0) {

                sendRedirect("../cargame/cargame.jsp", JSON.stringify(this.user));
            } else if (this.user.mode == 1) {

                sendRedirect("../cardgame/cardgame.jsp", JSON.stringify(this.user));
            }
        });

        document.getElementById("game-select").selectedIndex = this.user.mode;
    } else {

        console.log("ERROR: The Game Mode Must Be Defined");
    }

    document.getElementById("music-control").addEventListener("click", function () {

        user.music.isPlaying = !user.music.isPlaying;
        if (user.music.isPlaying) {

            audio.play();
            return;
        }
        audio.pause();
    });

    document.getElementById("view-score").addEventListener("click", function () {

        user.scoreVisible = !user.scoreVisible;

        scoreContainer.style['visibility'] = user.scoreVisible ? "visible" : "hidden";
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

        xhr.open("GET", "../write?login='" + user.name + "'&score=" + user.score + "&credit=" + user.credit);
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

                    if (msg) {

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