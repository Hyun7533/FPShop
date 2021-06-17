package com.fpshop.board;

import com.nimbusds.jose.crypto.impl.MACProvider;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONStyle;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.fpshop.model.board.BoardDTO;
import com.fpshop.model.board.BoardEntity;
import com.fpshop.model.Const;
import com.fpshop.security.IAuthenticationFacade;
import com.fpshop.security.model.UserPrincipal;

import lombok.RequiredArgsConstructor;

import java.util.AbstractMap;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor	
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardMapper mapper;
	@Autowired
	final BoardService service;
	@Autowired
    private IAuthenticationFacade authenticationFacade;


	@CrossOrigin
	@GetMapping("/qaList")
	public JSONObject qaList(BoardDTO p) {
		JSONObject jo = new JSONObject();
		jo.put("board", p.getTitle());
		return jo;
	}

	@GetMapping("/upBoard")
	public JSONArray upBoard() {
		JSONObject wyObj = new JSONObject();
		JSONObject snObj = new JSONObject();
		JSONArray componentArray = new JSONArray();
		wyObj.put("id","1");
		wyObj.put("entryName","장원영");
		wyObj.put("title","이쁘다");
		wyObj.put("thumbnail","http://supercup.co.kr/entryImg/d11be93604df.gif");
		wyObj.put("description","이쁘게 움직이는 장원영 움짤");
		wyObj.put("regDate","2018-12-14 15:15:59");
		wyObj.put("url","http://supercup.co.kr/entry/detail?type=girlIdol&entryId=131");
		snObj.put("id","2");
		snObj.put("entryName","사나");
		snObj.put("title","귀엽다");
		snObj.put("thumbnail","http://supercup.co.kr/entryImg/bd7b77a5fc7c.jpg");
		snObj.put("description","시상식 사나 클라스");
		snObj.put("regDate","2018-11-19 13:30:44");
		snObj.put("url","http://supercup.co.kr/entry/detail?type=girlIdol&entryId=56");
		componentArray.add(wyObj);
		componentArray.add(snObj);

		return componentArray;
	}
	
	@GetMapping("/qadetail")
	public void qaDetail(BoardDTO p, Model model) {
		//UserPrincipal user = authenticationFacade.getUserPrincipal();
		//p.setUser_pk(user.getUser_pk());
		model.addAttribute(Const.KEY_DATA, service.selBoard(p));
	}
	
	// 수정 
	@GetMapping("/qaregmod")
	public void qaRegMod() {}
	
	@PostMapping("/qamod")
		public void qaMod() {
	}
	
	
	
	@PostMapping("/qareg")
	public String qaReg(BoardEntity p) {
		UserPrincipal user = authenticationFacade.getUserPrincipal();
		p.setUser_pk(user.getUser_pk());
		service.insBoard(p);
		
	return  "redirect:/board/qalist";
	
	//return "redirect:/board/qaList?board_no=" + p.getBoard_no();
	}
	
}
