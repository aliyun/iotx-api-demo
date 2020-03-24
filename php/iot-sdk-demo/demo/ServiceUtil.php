<?php
namespace MyUtil;
/*
 * Copyright (c) 2014-2016 Alibaba Group. All rights reserved.
 * License-Identifier: Apache-2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
class ServiceUtil
{
    public static function productNameGenerator()
    {
        $chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
        $name = 'product_';
        $length = 4;
        for ($i = 0; $i < $length; $i++)
        {
            $name .= $chars[mt_rand(0, strlen($chars) - 1)];
        }
        return $name;
    }

    public static function deviceNameListGenertor($length)
    {
        $chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
        $deviceNameArray = array();
        for ($i = 0; $i < $length; $i++)
        {
            $name = 'device_';
            $l = 4;
            for ($j = 0; $j < $l; $j++)
            {
                $name .= $chars[mt_rand(0, strlen($chars) - 1)];
            }

            array_push($deviceNameArray,$name);
        }
        return $deviceNameArray;
    }

    public static function deviceNameGenertor($l)
    {
        $chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
        $name = 'device_';
        for ($j = 0; $j < $l; $j++) {
            $name .= $chars[mt_rand(0, strlen($chars) - 1)];
        }

        return $name;
    }

    public static function object2array($object) {
        $object =  json_decode( json_encode( $object),true);
        return  $object;
    }

}
