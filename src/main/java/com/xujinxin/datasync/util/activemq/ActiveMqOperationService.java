package com.xujinxin.datasync.util.activemq;

import com.xujinxin.datasync.bean.User;
import com.xujinxin.datasync.enums.ErrorType;
import com.xujinxin.datasync.exception.ValidException;
import com.xujinxin.datasync.util.SerializeService;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class ActiveMqOperationService<T> {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String sql = "insert into user(username,password) values(?,?)";
    private static final Integer dbIndex = 1;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ActiveMQTopic topic;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private SerializeService serializeService;


    @JmsListener(destination = "queue.data.sync", containerFactory = "queueListenerContainerFactory")
    private void receive(User user) {
        LOGGER.info(user);
        String username = user.getUsername();
        String password = user.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ValidException(ErrorType.DATA_INVALID);
        }
        int rows = jdbcTemplate.update(sql, username, password);
        if (rows == 0) {
            LOGGER.error("注册失败{}", user);
        }
    }

    @JmsListener(destination = "topic.data.sync", containerFactory = "topicListenerContainerFactory")
    private void listen(User user) {
        LOGGER.info(user);
    }

    public void send(User user) {
        LOGGER.info(user.toString());
        jmsTemplate.convertAndSend(topic, serializeService.serialize(user));
    }
}