/**************************************************************************
/* This class implements a test of the ufraw-batch command.
/*
/* Copyright (c) 2009 by Bernhard Bablok (mail@bablokb.de)
/*
/* This program is free software; you can redistribute it and/or modify
/* it under the terms of the GNU Library General Public License as published
/* by  the Free Software Foundation; either version 2 of the License or
/* (at your option) any later version.
/*
/* This program is distributed in the hope that it will be useful, but
/* WITHOUT ANY WARRANTY; without even the implied warranty of
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
/* GNU Library General Public License for more details.
/*
/* You should have received a copy of the GNU Library General Public License
/* along with this program; see the file COPYING.LIB.  If not, write to
/* the Free Software Foundation Inc., 59 Temple Place - Suite 330,
/* Boston, MA  02111-1307 USA
/**************************************************************************/

package org.im4java.test;

import java.io.*;
import org.im4java.core.*;

/**
   This class implements a test of the ufraw-batch command.

   @version $Revision: 1.2 $
   @author  $Author: bablokb $
 
   @since 1.0.0
 */

public class TestCase17 extends AbstractTestCase {

  //////////////////////////////////////////////////////////////////////////////

  /**
     Return the description of the test.
  */

  public String getDescription() {
    return "ufraw-batch";
  }

  //////////////////////////////////////////////////////////////////////////////

  /**
     Main method. Just calls AbstractTestCase.runTest(), which catches and
     prints exceptions.
  */

  public static void main(String[] args) {
    TestCase17 tc = new TestCase17();
    tc.runTest(args);
  }

  //////////////////////////////////////////////////////////////////////////////

  /**
     Run the test.
  */

  public void run() throws Exception {
    System.err.println("17. Testing ufraw ...");
    String outfile=iImageDir+"rawfile.tif";
    String infile=System.getProperty("im4java.testUFRaw.infile");
    if (infile == null) {
      System.err.println(
         "\nSkipping this test since input-file is not defined.\n" +
         "Set the system-property im4java.testUFRaw.infile to\n" +
         "your input-file for ufraw:\n" +
         "\tpass JAVA_OPTS=-Dim4java.testUFRaw.infile=... to \"make test\" or\n" +
         "\texport JAVA_OPTS=-Dim4java.testUFRaw.infile=...\n\n"
      );
      return;
    }

    UFRawOperation op = new UFRawOperation();
    op.exposure("auto");
    op.outType("tif");
    op.size(800);
    op.createId("no");
    op.overwrite();
    op.output(outfile);
    op.addImage(infile);                                 // input-filename

    UFRawCmd ufraw = new UFRawCmd(true);                 // use batch-mode
    ufraw.run(op);

    DisplayCmd.show(outfile);
    (new File(outfile)).delete();
  }
}
