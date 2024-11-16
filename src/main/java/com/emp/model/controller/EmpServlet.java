package com.emp.model.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;

public class EmpServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("counterInformNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入訊息編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer counterInformNo = null;
				try {
					counterInformNo = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("櫃位訊息編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(counterInformNo);
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/listOneEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer counterInformNo = Integer.valueOf(req.getParameter("counterInformNo"));
				
				/***************************2.開始查詢資料****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(counterInformNo);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("empVO", empVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/emp/update_emp_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("update".equals(action)) { // 來自 update_emp_input.jsp 的請求

		    List<String> errorMsgs = new LinkedList<String>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    /*************************** 1.接收請求參數 - 輸入格式的錯誤處理**********************/
		    // 處理櫃位通知訊息編號
		    String counterInformNoStr = req.getParameter("counterInformNo").trim();
		    Integer counterInformNo = null;
		    if (counterInformNoStr != null && !counterInformNoStr.isEmpty()) {
		        try {
		            counterInformNo = Integer.valueOf(counterInformNoStr);
		        } catch (NumberFormatException e) {
		            errorMsgs.add("無效的櫃位通知訊息編號");
		        }
		    } else {
		        errorMsgs.add("櫃位通知訊息編號不可為空");
		    }

		    // 處理內文
		    String informMsg = req.getParameter("informMsg").trim();
		    if (informMsg == null || informMsg.trim().length() == 0) {
		        errorMsgs.add("通知內文請勿空白");
		    }

		    // 處理通知時間
		    java.sql.Date informDate = null;
		    String informDateStr = req.getParameter("informDate").trim();
		    try {
		        if (informDateStr != null && !informDateStr.isEmpty()) {
		            informDate = java.sql.Date.valueOf(informDateStr);  // 使用 java.sql.Date.valueOf 進行轉換
		        } else {
		            throw new IllegalArgumentException("請輸入日期");
		        }
		    } catch (IllegalArgumentException e) {
		        informDate = new java.sql.Date(System.currentTimeMillis()); // 設為當前時間
		        errorMsgs.add("無效的通知時間，請使用正確的日期格式");
		    }

		    // 建立 EmpVO 物件
		    EmpVO empVO = new EmpVO();
		    empVO.setCounterInformNo(counterInformNo);
		    empVO.setInformMsg(informMsg);
		    empVO.setInformDate(informDate);

		    // 如果有錯誤，將 empVO 放入 request 並返回修改頁面
		    if (!errorMsgs.isEmpty()) {
		        req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req
		        RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
		        failureView.forward(req, res);
		        return; // 程式中斷
		    }

		    /*************************** 2.開始修改資料*****************************************/
		    EmpService empSvc = new EmpService();
		    empVO = empSvc.updateEmp(counterInformNo, informMsg, informDate);

		    // 如果更新後的 empVO 為 null，則處理錯誤
		    if (empVO == null) {
		        errorMsgs.add("資料更新失敗");
		        RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/update_emp_input.jsp");
		        failureView.forward(req, res);
		        return;
		    }

		    /*************************** 3.修改完成,準備轉交(Send the Success view)*************/
		    req.setAttribute("empVO", empVO); // 資料庫 update 成功後,正確的 empVO 物件存入 req
		    String url = "/back-end/emp/listOneEmp.jsp"; // 成功後轉交至顯示資料的頁面
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		}



		if ("insert".equals(action)) { // 來自 addEmp.jsp 的請求

		    List<String> errorMsgs = new LinkedList<String>();
		    req.setAttribute("errorMsgs", errorMsgs);

		    try {
		        /*********************** 1. 接收請求參數 - 輸入格式的錯誤處理 *************************/

		        // 櫃位訊息編號
		        Integer counterInformNo = null;
		        try {
		            String counterInformNoStr = req.getParameter("counterInformNo");
		            if (counterInformNoStr != null && !counterInformNoStr.trim().isEmpty()) {
		                counterInformNo = Integer.valueOf(counterInformNoStr.trim());
		            } else {
		                errorMsgs.add("櫃位訊息編號勿空白");
		            }
		        } catch (NumberFormatException e) {
		            errorMsgs.add("櫃位訊息編號格式不正確");
		        }

		        // 櫃位編號
		        Integer counterNo = null;
		        try {
		            String counterNoStr = req.getParameter("counterNo");
		            if (counterNoStr != null && !counterNoStr.trim().isEmpty()) {
		                counterNo = Integer.valueOf(counterNoStr.trim());
		            } else {
		                errorMsgs.add("櫃位編號勿空白");
		            }
		        } catch (NumberFormatException e) {
		            errorMsgs.add("櫃位編號格式不正確");
		        }

		        // 內文訊息
		        String informMsg = req.getParameter("informMsg"); 
		        if (informMsg == null || informMsg.trim().isEmpty()) {
		            errorMsgs.add("內文請勿空白");
		        } else {
		            informMsg = informMsg.trim();
		        }

		        // 發布時間
		        java.sql.Date informDate = null;
		        try {
		            String informDateStr = req.getParameter("informDate");
		            if (informDateStr != null && !informDateStr.trim().isEmpty()) {
		                informDate = java.sql.Date.valueOf(informDateStr.trim()); 
		            } else {
		                errorMsgs.add("請輸入發布時間");
		            }
		        } catch (IllegalArgumentException e) {
		            errorMsgs.add("發布時間格式不正確");
		        }

		        // 處理 informRead（已讀狀態）
		        Integer informRead = null;
		        String informReadStr = req.getParameter("informRead");
		        if (informReadStr != null && !informReadStr.trim().isEmpty()) {
		            try {
		                informRead = Integer.valueOf(informReadStr.trim());
		            } catch (NumberFormatException e) {
		                errorMsgs.add("內文已讀狀態格式不正確");
		            }
		        } else {
		            informRead = 0; // 預設為 0
		        }

		        // 將輸入資料封裝進 VO
		        EmpVO empVO = new EmpVO();
		        empVO.setCounterInformNo(counterInformNo);
		        empVO.setCounterNo(counterNo);
		        empVO.setInformMsg(informMsg);
		        empVO.setInformDate(informDate);
		        empVO.setInformRead(informRead);  // 加入 informRead

		        // 如果有錯誤，返回輸入頁面
		        if (!errorMsgs.isEmpty()) {
		            req.setAttribute("empVO", empVO); 
		            RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
		            failureView.forward(req, res);
		            return;
		        }

		        /*************************** 2. 開始新增資料 ***************************************/
		        EmpService empSvc = new EmpService();
		        empVO = empSvc.addEmp(counterInformNo, counterNo, informMsg, informDate); // 傳遞 informRead

		        /*************************** 3. 新增完成, 準備轉交(Send the Success view) ***********/
		        String url = "/back-end/emp/listAllEmp.jsp";
		        RequestDispatcher successView = req.getRequestDispatcher(url);
		        successView.forward(req, res);

		    } catch (Exception e) {
		        errorMsgs.add("無法取得資料：" + e.getMessage());
		        RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
		        failureView.forward(req, res);
		    }
		}


		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer counterInformNo = Integer.valueOf(req.getParameter("counterInformNo"));
				
				/***************************2.開始刪除資料***************************************/
				EmpService empSvc = new EmpService();
				empSvc.deleteEmp(counterInformNo);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/emp/listAllEmp.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
}
