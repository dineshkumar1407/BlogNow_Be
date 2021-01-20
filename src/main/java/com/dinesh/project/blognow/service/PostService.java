package com.dinesh.project.blognow.service;

import com.dinesh.project.blognow.dto.PostResponseDto;
import com.dinesh.project.blognow.exceptions.PostNotFoundException;
import com.dinesh.project.blognow.exceptions.SubredditNotFoundException;
import com.dinesh.project.blognow.mapper.PostMapper;
import com.dinesh.project.blognow.repository.PostRepository;
import com.dinesh.project.blognow.repository.SubredditRepository;
import com.dinesh.project.blognow.repository.UserRepository;
import com.dinesh.project.blognow.dto.PostRequestDto;
import com.dinesh.project.blognow.model.Post;
import com.dinesh.project.blognow.model.Subreddit;
import com.dinesh.project.blognow.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PostMapper postMapper;

    public void save(PostRequestDto postRequestDto) {
        Subreddit subreddit = subredditRepository.findByName(postRequestDto.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequestDto.getSubredditName()));
        postRepository.save(postMapper.map(postRequestDto, subreddit, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
}
