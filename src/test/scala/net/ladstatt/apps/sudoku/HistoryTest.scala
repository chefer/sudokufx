package net.ladstatt.apps.sudoku

import net.ladstatt.apps.sudoku.Parameters._
import net.ladstatt.core.Utils
import org.junit.Assert._
import org.junit.Test

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.{Failure, Try}

/**
 * Created by lad on 27.04.14.
 */
class HistoryTest extends OpenCvUnitTest with Utils {


  @Test def detectInvalidSector(): Unit = {
    val r = Await.result(emptySudoku.currentState.computeSolution(), Duration.Inf)
    assertTrue(0 == emptySudoku.currentState.hCounts(0)(0))
  }

  def checkStats(expected: String, result: SudokuResult): Unit = {
    assertEquals(expected, result match {
      case SFailure(candidate) => candidate.currentState.statsAsString()
      case SSuccess(candidate, _, _, _, _) => candidate.currentState.statsAsString()
    })
  }

  @Test
  def detectSudoku69(): Unit = {
    checkStats( """Quality:
                  |--------
                  |1.7976931348623157E308
                  |9623698.0
                  |6697577.0
                  |8193134.0
                  |9688721.0
                  |8128137.0
                  |1.0664098E7
                  |7282804.0
                  |9688727.0
                  |8193166.0
                  |
                  |Hitcounts:
                  |----------
                  |
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,1,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,1,0,0,0,0
                  |0,0,0,1,0,0,0,0,0,0
                  |0,0,1,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,1,0,0,0,0,0,0
                  |0,0,0,0,0,0,1,0,0,0
                  |0,1,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,1,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,1
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,1,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,1,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,1,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,1,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,1,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,1,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,1,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,1,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,1,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,1,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,1,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,1,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,1,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,1
                  |0,0,1,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,1,0
                  |0,0,0,0,0,0,0,0,0,1
                  |0,0,0,1,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,1,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |1,0,0,0,0,0,0,0,0,0
                  |
                  | """.stripMargin, sudoku69Result)
  }

  @Test def detectEmptyCells() = {
    checkStats( """Quality:
                  |--------
                  |1.7976931348623157E308
                  |1.7976931348623157E308
                  |1.7976931348623157E308
                  |1.7976931348623157E308
                  |1.7976931348623157E308
                  |1.7976931348623157E308
                  |1.7976931348623157E308
                  |1.7976931348623157E308
                  |1.7976931348623157E308
                  |1.7976931348623157E308
                  |
                  |Hitcounts:
                  |----------
                  |
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |0,0,0,0,0,0,0,0,0,0
                  |
                  | """.stripMargin, emptySudokuResult)
  }

  @Test
  def invalidSCell() = {
    Try {
      SCell(-10, 0, null)
    } match {
      case Failure(e: AssertionError) =>
      case _ => fail("should throw an AssertionError")
    }
  }

  @Test
  def invalidSCell2() = {
    Try {
      SCell(0, -1, null)
    } match {
      case Failure(e: AssertionError) =>
      case _ => fail("should throw an AssertionError")
    }
  }

  @Test def testDetectInvalidHitcounts(): Unit = {
    val hCounts = Array.fill(cellRange.size)(Array.fill[SCount](digitRange.size)(0))
    assertTrue(SCandidate.isValid(hCounts, Seq(0), 1))
  }


  @Test def testDetectInvalidHitcounts1(): Unit = {
    val hCounts = Array.fill(cellRange.size)(Array.fill[SCount](digitRange.size)(0))
    assertTrue(SCandidate.isValid(hCounts, Seq(1), 1))
  }


  @Test def testDetectInvalidHitcounts2(): Unit = {
    val hCounts = Array.fill(cellRange.size)(Array.fill[SCount](digitRange.size)(5))
    assertTrue(!SCandidate.isValid(hCounts, Seq(1), 1))
  }


}
