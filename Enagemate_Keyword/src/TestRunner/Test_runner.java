package TestRunner;

import java.awt.AWTException;

import org.testng.annotations.Test;

import Reusable.Reusable_class;
import Reusable.Reusable_class;

public class Test_runner {
	//@Tests
	public void StartExecution (String sheetN) throws InterruptedException,AWTException  {

		Reusable_class func=new Reusable_class();
		String[][] keywordsData=func.fetchDataFromExcel();

		int rownum=keywordsData.length;
		int colnum=keywordsData[0].length;

		String TC_Name=keywordsData[0][0];
		String TC_Step_No=keywordsData[0][1];
		String TC_Step_Name=keywordsData[0][2];
		String TC_Func=keywordsData[0][3];
		String TC_Location=keywordsData[0][4];
		String TC_LV=keywordsData[0][5];
		String TC_Param=keywordsData[0][6];
		String TC_Execute=keywordsData[0][7];

		for (int counter =1; counter < rownum; counter++) {
			String function = keywordsData[counter][3];
			String LocatorBy = keywordsData[counter][4];
			String locatorElement = func.fetchprop(keywordsData[counter][5]);
			String content_param = keywordsData[counter][6];
			String execute=keywordsData[counter][7];
			if (execute.equals("Y")) {
				System.out.println(function + "\t" + LocatorBy + "\t" + locatorElement + "\t" + content_param);

				switch (function) {
				case "LaunchAppl" :
					func.LaunchAppl();
					break;
				case "FillText":
					func.FillText(LocatorBy, locatorElement, content_param);
					break;
				case "FillNumber":
					func.FillNumber(LocatorBy, locatorElement, content_param);
					break;
				case "click":
					func.click(LocatorBy, locatorElement);
					break;
				case "isPresent":
					func.isPresent(LocatorBy, locatorElement);
					break;
				case "Scroll_Ele_click":
					func.Scroll_Ele_click(LocatorBy, locatorElement);
					break;
				case "Wait":
					func.Wait();
					break;
				case "Wait_5":
					func.Wait_5();
					break;
				case "Wait_7":
					func.Wait_7();
					break;
				case "Wait_10":
					func.Wait_10();
					break;
				case "Wait_15":
					func.Wait_15();
					break;
				case "Implicit_wait":
					func.Implicit_wait();
					break;
				case "scroll":
					func.scroll();
					break;
				case "enter":
					func.enter();
					break;
				case "escape":
					func.escape();
					break;
				case "refresh_browser":
					func.refresh_browser();
					break;
				case "getActvityID":
					func.getActvityID();
					break;
				case "sendActivityId":
					func.sendActivityId();
					break;
				case "getActvityIDForCloseOut":
					func.getActvityIDForCloseOut();
					break;
				case "sendActivityIdOfCloseout2":
					func.sendActivityIdOfCloseout2(LocatorBy, locatorElement);;
					break;
				case "sendActivityIdOfCloseout1":
					func.sendActivityIdOfCloseout1();;
					break;
				case "clickon":
					func.clickon(null, null, null);
					break;
				case "UploadDocuments":
					func.UploadDocuments();
					break;
				case "approval":
					func.approval();
					break;
				case "validateNominee":
					func.validateNominee();
					break;
				case "validate_Activity_Status":
					func.validate_Activity_Status();
					break;
				case "explicit_wait":
					func.explicit_wait(locatorElement);
					break;
				case "validateValue":
					func.validateValue(LocatorBy, locatorElement, content_param);

				}
			}

		}
	}


}
