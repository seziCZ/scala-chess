/*
 * Copyright (c) 2017 Tomas Sezima
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */
package cz.sezima.chess.api.player

import scala.util.Random

import cz.sezima.chess.api.Chess
import cz.sezima.chess.core.Colors.{Black, White}
import cz.sezima.chess.core.board.Board
import cz.sezima.chess.core.piece._
import org.scalatest.FunSuite

/**
  * A set of [[AlphaBetaPlayer]] relevant unit tests.
  */
class AlphaBetaPlayerTests extends FunSuite {

  val randomPlayer: Player = new Player {
    override def play(board: Board): Move =
      Random.shuffle(board.genBoards(board.onMove)).head.history.head
  }

  val αβPlayer: Player =
    AlphaBetaPlayer(2)

  /**
    * What are the odds random player would win?
    */
  test("black alpha-beta player beats random white player") {

    // act
    val result: Board =
      Chess.play(randomPlayer, αβPlayer)

    // assert
    assert(result.isCheckmated(White), result)
  }

  test("white alpha-beta player beats random black player") {

    // act
    val result: Board =
      Chess.play(αβPlayer, randomPlayer)

    // assert
    assert(result.isCheckmated(Black), result)
  }
}
