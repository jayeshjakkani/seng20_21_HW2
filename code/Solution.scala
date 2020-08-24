import scala.annotation.tailrec
import scala.collection.immutable.Queue
import scala.util.Try

/*
 * https://leetcode.com/problems/game-of-life/
 *
 * Basically it's a classic BFS, with some more wrangling of data to do it in place
 * You need to define some special ints which indicate the previous value and the value
 * which the cell will become. Then at the end you mutate these to be the their updated
 * value
 *
 */

object Solution {

  case class Cell(row: Int, column: Int)

  object Values {
    val ZERO = 0
    val ONE = 1
    val ZERO_TO_ONE = 2 // was a zero, will be a one
    val ONE_TO_ZERO = 3 // Was a 1, will be a zero

    def isZero(i: Int): Boolean = i == ZERO || i == ZERO_TO_ONE
    def isOne(i: Int): Boolean = i == ONE || i == ONE_TO_ZERO
  }

  def updateGameboard(board: Array[Array[Int]], cell: Cell): Unit = {
    val boardCell = board(cell.row)(cell.column)
    val neighbors = getNeighbors(board, cell).toList.map(each => board(each.row)(each.column))
    val countOnes = neighbors.count(each => Values.isOne(each))

    if(countOnes < 2 && boardCell == 1) {
      board(cell.row)(cell.column) = Values.ONE_TO_ZERO
    } else if(countOnes > 3 && boardCell == 1) {
      board(cell.row)(cell.column) = Values.ONE_TO_ZERO
    } else if(countOnes == 3 && boardCell == 0) {
      board(cell.row)(cell.column) = Values.ZERO_TO_ONE
    }
  }

  def getNeighbors(board: Array[Array[Int]], cell: Cell): Set[Cell] = {
    val row = cell.row
    val column = cell.column

    Set(
      Try {
        board(row - 1)(column)
        Cell(row - 1, column)
      },
      Try {
        board(row)(column - 1)
        Cell(row, column - 1)
      },
      Try {
        board(row + 1)(column)
        Cell(row + 1, column)
      },
      Try {
        board(row)(column + 1)
        Cell(row, column + 1)
      },
      Try {
        board(row - 1)(column - 1)
        Cell(row - 1, column - 1)
      },
      Try {
        board(row + 1)(column + 1)
        Cell(row + 1, column + 1)
      },
      Try {
        board(row - 1)(column + 1)
        Cell(row - 1, column + 1)
      },
      Try {
        board(row + 1)(column - 1)
        Cell(row + 1, column - 1)
      }
    ).filter(_.isSuccess).map(_.get)
  }

  @tailrec
  def bfs(board: Array[Array[Int]],
          queue: Queue[Cell],
          seenSet: Set[Cell]): Unit = {

    queue.dequeueOption match {
      case Some((cell, queuePrime)) =>
        if(!seenSet(cell)) {
          updateGameboard(board, cell)
          val neighbors = getNeighbors(board, cell)
          val neighborsForProcessing = neighbors.filterNot(each => seenSet(each))
          bfs(board, queuePrime.enqueueAll(neighborsForProcessing), seenSet ++ Set(cell))
        } else {
          bfs(board, queuePrime, seenSet)
        }
      case None => ()
    }
  }


  def gameOfLife(board: Array[Array[Int]]): Unit = {

    val queue = Queue[Cell]()
    val initialCell = Cell(0,0)
    if(board.isEmpty || board.exists(_.isEmpty)) {
      ()
    } else {
      bfs(board,queue.enqueue(initialCell), Set.empty[Cell])
      for(row <- board.indices) {
        for(column <- board(row).indices) {
          if(board(row)(column) == Values.ONE_TO_ZERO) {
            board(row)(column) = 0
          } else if(board(row)(column) == Values.ZERO_TO_ONE) {
            board(row)(column) = 1
          }
        }
      }
    }
  }

  private def printBoard(board: Array[Array[Int]]): Unit = {
    for(i <- board.indices) {
      println(board(i).toList)
    }
  }

  def main(args: Array[String]): Unit = {
    val board = Array(
      Array(0,1,0),
      Array(0,0,1),
      Array(1,1,1),
      Array(0,0,0),
    )
    gameOfLife(board)
    gameOfLife(Array(Array()))
    printBoard(board)
  }
}
