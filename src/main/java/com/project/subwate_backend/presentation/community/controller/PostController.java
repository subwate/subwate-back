package com.project.subwate_backend.presentation.community.controller;

import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.community.dto.request.PostRequestDto;
import com.project.subwate_backend.presentation.community.dto.response.PostDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController("/api/vi/comunity/post")
public class PostController {

    @Operation(summary = "게시물 리스트 조회", description = "조건에 따른 게시물 리스트 조회한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "게시물 리스트 조회 성공했습니다.",
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "로그인이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @GetMapping("/list")
    public ResponseDto<Page<PostDto>> getPostList(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(defaultValue = "createdAt,desc") String sort,
                                                  @RequestParam(required = false) String keyword,
                                                  @Parameter(description = "필터링 할 호선") @RequestParam(required = false) String subwayLineId) {
        return ResponseDto.of(HttpStatus.OK, "게시물 리스트 조회에 성공했습니다.", new PageImpl<>(new ArrayList<PostDto>(), PageRequest.of(page, size), 0));
    }

    @Operation(summary = "게시물 상세 정보 조회", description = "특정 게시물의 내용 조회한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "게시물 조회에 성공했습니다.",
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "로그인이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @GetMapping
    public ResponseDto<PostDto> getPost(@Parameter(description = "조회 할 게시물 id", required = true) @RequestParam Long postId) {
        return ResponseDto.of(HttpStatus.OK, "게시물 조회에 성공했습니다.", new PostDto());
    }

    @Operation(summary = "게시물 업로드", description = "게시물을 업로드한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "게시물 업로드에 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = PostRequestDto.class)),
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "로그인이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @PostMapping("/upload")
    public ResponseDto<PostDto> uploadPost(@Parameter(description = "게시물에 작성 할 내용", required = true) @RequestBody PostRequestDto postRequestDto) {
        return ResponseDto.of(HttpStatus.OK, "게시물 업로드에 성공했습니다.", new PostDto());
    }

    @Operation(summary = "게시물 삭제", description = "게시물을 삭제한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "게시물 업로드에 성공했습니다.",
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "로그인이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @DeleteMapping("/upload")
    public ResponseDto<Void> deletePost(@Parameter(description = "삭제할 게시물 id", required = true) @RequestParam Long postId) {
        return ResponseDto.of(HttpStatus.OK, "게시물 삭제에 성공했습니다.", null);
    }

    @Operation(summary = "게시물 수정", description = "게시물 내용을 수정한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "게시물 수정에 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = PostRequestDto.class)),
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "로그인이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @PostMapping("/update")
    public ResponseDto<PostDto> updatePost(@Parameter(description = "수정 할 게시물 내용", required = true) @RequestBody PostRequestDto postRequestDto) {
        return ResponseDto.of(HttpStatus.OK, "게시물 수정에 성공했습니다.", new PostDto());
    }

    @Operation(summary = "게시물 좋아요", description = "게시물에 좋아요를 누른다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "게시물 좋아요에 성공했습니다.",
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "로그인이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @PutMapping("/{postId}/like")
    public ResponseDto<Void> likePost(@Parameter(description = "좋아요를 할 게시물 id", required = true) @PathVariable Long postId) {
        return ResponseDto.of(HttpStatus.OK, "게시물 좋아요에 성공했습니다.", null);
    }

    @Operation(summary = "게시물 좋아요 취소", description = "좋아요를 누른 게시물을 취소한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "게시물 좋아요 취소에 성공했습니다.",
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "로그인이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @DeleteMapping("/{postId}/like")
    public ResponseDto<Void> unlikePost(@Parameter(description = "좋아요 취소 할 게시물 id", required = true) @PathVariable Long postId) {
        return ResponseDto.of(HttpStatus.OK, "게시물 좋아요 취소에 성공했습니다.", null);
    }

    @Operation(summary = "게시물 댓글 작성", description = "게시물에 댓글을 작성한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "댓글 작성에 성공했습니다",
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "로그인이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @PostMapping("/{postId}/comments")
    public ResponseDto<Void> addComment(@PathVariable Long postId) {
        return ResponseDto.of(HttpStatus.OK, "댓글 작성에 성공했습니다.", null);
    }

    @Operation(summary = "게시물 댓글 작성", description = "게시물에 댓글을 삭제한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "댓글 삭제에 성공했습니다",
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "로그인이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @DeleteMapping("/{postId}/comments")
    public ResponseDto<Void> deleteComment(@PathVariable Long postId, @RequestBody String commentText) {
        return ResponseDto.of(HttpStatus.OK, "댓글 삭제에 성공했습니다.", null);
    }

    @Operation(summary = "게시물 댓글에 대댓글 작성", description = "게시물 댓글에 대댓글을 작성한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "게시물 대댓글 업로드에 성공했습니다.",
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "로그인이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @PostMapping("/{postId}/comments/{commentId}/replies")
    public ResponseDto<Void> addReply(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody String commentText) {
        return ResponseDto.of(HttpStatus.OK, "게시물 대댓글 업로드에 성공했습니다.", null);
    }

    @Operation(summary = "게시물 댓글에 대댓글 삭제", description = "게시물 댓글에 대댓글을 삭제한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "게시물 대댓글 삭제에 성공했습니다.",
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "로그인이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @DeleteMapping("/{postId}/comments/{commentId}/replies")
    public ResponseDto<Void> deleteReply(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody String commentText) {
        return ResponseDto.of(HttpStatus.OK, "게시물 대댓글 삭제에 성공했습니다.", null);
    }

    @Operation(summary = "인기 게시물 조회", description = "인기 게시물 리스트 조회에 성공했습니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "인기 게시물 리스트 조회에 성공했습니다.",
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "로그인이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @GetMapping("/popular")
    public ResponseDto<Page<PostDto>> getPopularPosts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return ResponseDto.of(HttpStatus.OK, "인기 게시물 리스트 조회에 성공했습니다.", new PageImpl<>(new ArrayList<PostDto>(), PageRequest.of(page, size), 0));
    }
}
