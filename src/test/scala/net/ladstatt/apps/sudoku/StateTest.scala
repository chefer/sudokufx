package net.ladstatt.apps.sudoku

import net.ladstatt.opencv.OpenCV
import org.junit.Assert._
import org.junit.Test
import org.opencv.core.Mat

/**
 * Created by lad on 12.01.15.
 */
class StateTest {

  OpenCV.loadNativeLib()

  def compare(a: SCell, b: SCell): Boolean = {
    false
  }

  def compare(a: SudokuState, b: SudokuState): Boolean = {
    (a.hCounts.deep == b.hCounts.deep) &&
      (a.digitQuality.deep == b.digitQuality.deep) &&
      (a.digitData.deep == b.digitData.deep) &&
      (a.cap == b.cap) &&
      (a.minHits == b.minHits) &&
      (a.cells.size == b.cells.size) &&
      (a.cells zip b.cells).forall { case x => x._1.equals(x._2)}
    // && (a.digitLibrary == b.digitLibrary)
  }

  @Test def stateMergeTest(): Unit = {
    assertTrue(compare(SudokuState(), SudokuState().mergeN(SudokuState())))
  }

  def asMat(i: Int): Mat = Parameters.templateLibrary(i)

  val cellData = new Mat
  val cellz = Seq(SCell(1, 112.1212, asMat(1)), SCell(4, 80.1212, asMat(4)))
  val s0 = SudokuState(cells = cellz)

  /*
    @Test def stateMergeTest14(): Unit = assertEquals(SudokuState(), SudokuState().mergeN(SudokuState()))
    @Test def stateMergeTest1(): Unit = assertEquals(SudokuState(), SudokuState())
    @Test def stateMergeTest1_0(): Unit = assertEquals(s0, SudokuState().mergeN(s0))
    @Test def stateMergeTest1_1(): Unit = assertEquals(s0, s0.mergeN(SudokuState()))
    @Test def stateMergeTest1_2(): Unit = assertEquals(s0, s0.mergeN(s0))
    */
  @Test def stateMergeTest1(): Unit = assertTrue(compare(s0, SudokuState().mergeN(s0)))

  @Test def stateMergeTest2(): Unit = assertTrue(compare(s0, s0.mergeN(SudokuState())))

  @Test def stateMergeTest3(): Unit = assertTrue(compare(s0, s0.mergeN(s0)))


  // defines a poor man's comparison between matrixes
  def compareMat(a: Mat, b: Mat): (Boolean, String) = {
    val (aCols, aRows) = (a.cols, a.rows)
    val (bCols, bRows) = (b.cols, b.rows)
    val (aChan, bChan) = (a.channels(), b.channels())

    if ((aChan == bChan) && (aCols == bCols) && (aRows == bRows)) {
      if (a.dump == b.dump) {
        (true, "OK")
      } else (false, "matrices differ")
    } else (false, s"($aChan/$aCols/$aRows) != ($bChan/$bCols/$bRows)")
  }

  // should show that we can compare two empty mats successfully
  @Test def testCompareMatForEmptyMats(): Unit = {
    //assertEquals(112.1212, s0.mergeN(s0).digitLibrary(1)._1, 0.00001)
    //assertEquals(Some(cellData), s0.mergeN(s0).digitLibrary(1)._2)
    assertEquals((true, "OK"), compareMat(new Mat, new Mat))
  }

  // should test we can compare two non empty mats succesfully
  @Test def testTwoNonEmptyMats(): Unit = {
    for (m <- Parameters.templateLibrary.values) {
      assertEquals(m.dump, m.dump)
    }
  }

  // should test we can compare two non empty mats succesfully
  @Test def testDifferingMats(): Unit = {
    for (m <- Parameters.templateLibrary.values) {
      assertEquals((false, "(1/0/0) != (1/25/50)"), compareMat(new Mat, m))
    }
  }

  @Test def testEquality(): Unit = {
    assertEquals(112.1212, s0.mergeN(s0).digitLibrary(1)._1, 0.00001)
    assertEquals((true, "OK"), compareMat(asMat(1), s0.mergeN(s0).digitLibrary(1)._2.get))
  }
}