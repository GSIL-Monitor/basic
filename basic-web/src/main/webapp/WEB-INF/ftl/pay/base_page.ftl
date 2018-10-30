<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <@block name="pagetitle"></@block>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=750,target-densitydpi=device-dpi,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="no" />
    <meta name="format-detection" content="telephone=no" />
<#include "./default_cfg.ftl"/>
<#include "./base_css.ftl"/>
<@block name="cssfile"></@block>
<@block name="csstext"></@block>
</head>
<body>
<@block name="content">
</@block>
<@block name="footer">
</@block>
<@block name="window"></@block>
<#include "./base_js.ftl"/>
<@block name="jsfile"></@block>
<@block name="jstext"></@block>
<div id="hitoast"></div>
<div id="choose_branch_block"></div>
</body>
</html>