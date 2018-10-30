package com.wgb.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wgb on 2016/10/20.
 */
@ContextConfiguration(locations = {"file:src/test/resources/applicationContext.xml"})
public class TestNgTest extends AbstractTestNGSpringContextTests {
}
