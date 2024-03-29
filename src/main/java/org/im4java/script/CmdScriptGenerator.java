/**************************************************************************
/* This class implements a CmdScriptGenerator.
/*
/* Copyright (c) 2010 by Bernhard Bablok (mail@bablokb.de)
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

package org.im4java.script;

import java.util.*;
import java.io.*;
import org.im4java.core.*;

/**
   This class implements a CmdScriptGenerator.

   @version $Revision: 1.3 $
   @author  $Author: bablokb $
 
   @since 1.0.0
 */

public class CmdScriptGenerator extends AbstractScriptGenerator {

  //////////////////////////////////////////////////////////////////////////////

  /**
     The default constructor.
  */

  public CmdScriptGenerator() {
    ESC_EOL = '^';
    ESC_SPECIAL = '^';
  }

  //////////////////////////////////////////////////////////////////////////////

  /**
     Write the header of the script.
  */

  protected void writeHeader() {
    getWriter().println(
       "@echo off\n"+
       "REM -------------------------------------------------------\n"+
       "REM  Cmd-script autogenerated by im4java\n"+
       "REM  at " + Calendar.getInstance().getTime() + "\n"+
       "REM -------------------------------------------------------\n"
    );

    // add search-path
    String path=getSearchPath();
    if (path != null) {
      getWriter().println(
       "set PATH="+path+File.pathSeparator+"%PATH%\n"
      );
    }
  }

  //////////////////////////////////////////////////////////////////////////////

  /**
     Return the token as a script-argument. Normally, the argument token is
     only part of the script-argument if it contains a [wxh+x+y]-read-spec.
  */

  protected String getScriptArg(String pToken) {
    iArgIndex++;
    if (pToken.length()>Operation.IMG_PLACEHOLDER.length()) {
      return "\"%~"+iArgIndex+"\"" + 
	                  pToken.substring(Operation.IMG_PLACEHOLDER.length());
    } else {
      return "\"%~"+iArgIndex+"\"";
    }
  }

  //////////////////////////////////////////////////////////////////////////////

  /**
     Quote the given string.
  */

  protected String quote(String pString) {
    if (pString.indexOf('"')==-1) {
      return '"'+pString+'"';
    } else {
      return '"'+pString.replace("\"","'")+'"';
    }
  }
}
