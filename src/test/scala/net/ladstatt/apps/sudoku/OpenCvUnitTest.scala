package net.ladstatt.apps.sudoku

import net.ladstatt.opencv.OpenCV._
import org.opencv.core.{CvType, Mat}
import org.opencv.highgui.Highgui

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * Created by lad on 26.10.14.
 */
trait OpenCvUnitTest {

  loadNativeLib()

  lazy val frame69: Mat = Highgui.imread("src/test/resources/frame69.png")
  lazy val solution69 =
    """617948532
      |524361879
      |389725641
      |742816953
      |156439728
      |938257416
      |275683194
      |461592387
      |893174265""".stripMargin.replaceAll("\n", "").toCharArray
  lazy val sudoku69 = SCandidate(nr = 0, frame = frame69)

  lazy val (sudoku69Result,_,_) = time(Await.result(sudoku69.calc(SudokuState(),Map(),Map(),1,17), Duration.Inf), t => logInfo(s"emptyFrame: $t ms"))

  lazy val emptyFrame = new Mat(1280, 768, CvType.CV_8UC3)
  lazy val emptySudoku = SCandidate(nr = 0, frame = emptyFrame)
  lazy val (emptySudokuResult,_,_) = time(Await.result(emptySudoku.calc(SudokuState(),Map(),Map(),1,1), Duration.Inf), t => logInfo(s"emptyFrame: $t ms"))


}
