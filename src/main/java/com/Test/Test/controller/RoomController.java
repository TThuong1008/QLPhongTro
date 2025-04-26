package com.Test.Test.controller;


import com.Test.Test.models.Room;
import com.Test.Test.repository.PaymentRepository;
import com.Test.Test.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {
	@Autowired
	private PaymentRepository paymentRepository;
	
    @Autowired
    private RoomService roomService;

    @GetMapping
    public String listRooms(Model model, 
                           @RequestParam(required = false) String keyword,
                           @RequestParam(required = false, defaultValue = "tenantName") String searchType) {
        List<Room> rooms = roomService.search(keyword, searchType);
        model.addAttribute("rooms", rooms);
        model.addAttribute("keyword", keyword);
        model.addAttribute("searchType", searchType);
        return "room/list_room";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Room room = new Room();
        room.setRoomId(roomService.generateNextRoomId());
        model.addAttribute("room", room);
        model.addAttribute("paymentTypes", paymentRepository.findAll());
        return "room/create_room";
    }

    @PostMapping("/create")
    public String createRoom(@Valid @ModelAttribute("room") Room room, 
                            BindingResult result,
                            RedirectAttributes redirectAttributes,
                            Model model) {
        
        if (room.getStartDate() != null && room.getStartDate().isBefore(LocalDate.now())) {
            result.rejectValue("startDate", "error.room", "Ngày bắt đầu không được là ngày quá khứ");
        }
        
        if (result.hasErrors()) {
            model.addAttribute("paymentTypes", paymentRepository.findAll()); 
            return "room/create_room"; 
        }
        
        roomService.save(room);
        redirectAttributes.addFlashAttribute("successMessage", "Đã tạo thông tin phòng trọ thành công!");
        return "redirect:/rooms";
    }

    @PostMapping("/delete")
    public String deleteRooms(@RequestParam(value = "selectedRooms", required = false) List<String> selectedRooms,
                             RedirectAttributes redirectAttributes) {
        if (selectedRooms != null && !selectedRooms.isEmpty()) {
            roomService.deleteAll(selectedRooms);
            redirectAttributes.addFlashAttribute("successMessage", "Đã xóa thông tin phòng trọ thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Vui lòng chọn phòng trọ cần xóa!");
        }
        return "redirect:/rooms";
    }
    
    @PostMapping("/delete-confirm")
    @ResponseBody
    public String deleteConfirmation(@RequestParam("roomIds") String roomIds) {
        return "success";
    }
}
