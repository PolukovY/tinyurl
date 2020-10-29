package com.levik.shorturl.infa.api;

import com.levik.shorturl.infa.api.dto.ShortUrlRequest;
import com.levik.shorturl.infa.api.dto.ShortUrlResponse;
import com.levik.shorturl.infa.service.ShortUrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@Controller
@Api(value = "short-url")
@RequestMapping("/url")
public class ShortUrlController {

    private static final String REDIRECT = "redirect:%s";

    private final ShortUrlService shortUrlService;

    @ApiOperation(value = "Add LongUrl to convert it to short one", nickname = "addLongUrl", notes = "", response = ShortUrlResponse.class, tags={ "shortUrl", })
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "successful operation", response = ShortUrlResponse.class),
            @ApiResponse(code = 400, message = "Invalid inputUrl supplied") })
    @PostMapping
    public ResponseEntity<ShortUrlResponse> addLongUrl(@Valid @RequestBody ShortUrlRequest request) {
        var shortUrlResponse = shortUrlService.addLongUrl(request);
        return ResponseEntity.status(201).body(shortUrlResponse);
    }

    @ApiOperation(value = "Find LongUrl by shortUrlId", nickname = "getLongUrl", notes = "Returns LongUrl", tags={ "shortUrl", })
    @ApiResponses(value = {
            @ApiResponse(code = 302, message = "successful operation with send redirect to long url"),
            @ApiResponse(code = 404, message = "ShortUrlId not found") })
    @GetMapping("{shortUrlId}")
    public String getLongUrl(@PathVariable("shortUrlId") String shortUrlId) {
        var response = shortUrlService.getLongUrl(shortUrlId);
        return String.format(REDIRECT, response.getLongUrl());
    }
}
