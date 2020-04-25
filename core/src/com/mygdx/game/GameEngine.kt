package com.mygdx.game

import com.badlogic.gdx.Gdx
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

class GameEngine {
    companion object {
        val pi: Float = (PI).toFloat()

        //ball and platform
        var gameBall: Ball = Ball()
        var gamePlatform = Platform()

        var leftIsPressed: Boolean = false
        var rightIsPressed: Boolean = false

        var boost: Int = 1
        var bouncedir: Int = -1

        var gameIsOver: Boolean = false
        var score: Int = 0

        var timeOfGameOver: Long = 0
        var timeBeforeReset: Int = 3


        fun resetGame() {
            gameBall = Ball()
            gamePlatform = Platform()
            boost = 1
            bouncedir = -1
            gameIsOver = false
            score = 0
            timeOfGameOver = 0
        }

        fun update() {
            if (gameIsOver) {
                if (timeOfGameOver/1000 < System.currentTimeMillis()/1000 - timeBeforeReset) {
                    resetGame();
                } else {
                    return;
                }
            }
            gameBall.xBall += (gameBall.speed * cos(gameBall.vector)) * boost
            gameBall.yBall += (gameBall.speed * sin(gameBall.vector)) * boost
            if (boost != 1) boost--

            checkCollisions()

            if (leftIsPressed && rightIsPressed) {
                //do nothing for now
            } else if (leftIsPressed) {
                gamePlatform.xPlatform -= (gamePlatform.speed * 1.1f)
                if (gamePlatform.xPlatform < 64) gamePlatform.xPlatform = 64f
            } else if (rightIsPressed) {
                gamePlatform.xPlatform += (gamePlatform.speed * 1.1f)
                if (gamePlatform.xPlatform > 2000) gamePlatform.xPlatform = 2000f
            }
        }

        fun increaseGameSpeed() {
            boost = 3
            bouncedir = 0
            gameBall.speed += 2
            gamePlatform.speed += 3
        }

        fun increaseScore() {
            score++
        }

        fun checkCollisions() {
            //left boundary
            if (gameBall.xBall < 59 + gameBall.ballRadius && bouncedir != 1) {
                if (gameBall.vector > 0) {
                    gameBall.vector = pi - gameBall.vector
                } else {
                    gameBall.vector = -pi - gameBall.vector
                }
                boost = 3
                bouncedir = 1
            }

            //right boundary
            if (gameBall.xBall > 2496 - gameBall.ballRadius && bouncedir != 2) {
                if (gameBall.vector > 0) {
                    gameBall.vector = pi - gameBall.vector
                } else {
                    gameBall.vector = -pi - gameBall.vector
                }
                boost = 3
                bouncedir = 2
            }

            //upper boundary
            if (gameBall.yBall > 1373 - gameBall.ballRadius && bouncedir != 3) {
                gameBall.vector = ((Random.nextFloat() * pi / 1.3f) + pi * 0.3f) / 2 * -1 //nextFloar returns random value between 0 and 1
                boost = 3
                bouncedir = 3
                increaseScore()
            }

            //lower boundary
            if (gameBall.yBall < 30 + gameBall.ballRadius && bouncedir != 0) {
                if (gameBall.yBall < 0 && gameBall.xBall < gamePlatform.xPlatform - gameBall.ballRadius
                        || gameBall.xBall > gamePlatform.xPlatform + gamePlatform.platformLength + gameBall.ballRadius) {
                    gameIsOver = true
                    timeOfGameOver = System.currentTimeMillis();
                } else if (gameBall.xBall > gamePlatform.xPlatform - gameBall.ballRadius
                        && gameBall.xBall < gamePlatform.xPlatform + gamePlatform.platformLength + gameBall.ballRadius) {
                    gameBall.vector = ((Random.nextFloat() * pi / 1.3f) + pi * 0.3f) / 2 //nextFloar returns random value between 0 and 1
                    increaseGameSpeed()
                }
            }

            if (gameBall.vector > pi) {
                gameBall.vector %= pi
            }
            if (gameBall.vector < -1 * pi) {
                gameBall.vector = (gameBall.vector % pi) + pi
            }
        }
    }

}