/**
 * Copyright (C) 2017 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.atlasmap.actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

import io.atlasmap.api.AtlasFieldAction;
import io.atlasmap.spi.AtlasFieldActionInfo;
import io.atlasmap.v2.Action;
import io.atlasmap.v2.CollectionType;
import io.atlasmap.v2.Concatenate;
import io.atlasmap.v2.EndsWith;
import io.atlasmap.v2.FieldType;
import io.atlasmap.v2.Format;
import io.atlasmap.v2.IndexOf;
import io.atlasmap.v2.LastIndexOf;
import io.atlasmap.v2.PadStringLeft;
import io.atlasmap.v2.PadStringRight;
import io.atlasmap.v2.ReplaceAll;
import io.atlasmap.v2.ReplaceFirst;
import io.atlasmap.v2.StartsWith;
import io.atlasmap.v2.SubString;
import io.atlasmap.v2.SubStringAfter;
import io.atlasmap.v2.SubStringBefore;

public class StringComplexFieldActions implements AtlasFieldAction {

    public static final String STRING_SEPARATOR_REGEX = "^\\s+:_+=";
    public static final Pattern STRING_SEPARATOR_PATTERN = Pattern.compile(STRING_SEPARATOR_REGEX);

    @AtlasFieldActionInfo(name = "GenerateUUID", sourceType = FieldType.ALL, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
    public static String genareteUUID(Action action, Object input) {
        return UUID.randomUUID().toString();
    }

    @AtlasFieldActionInfo(name = "Concatenate", sourceType = FieldType.ALL, targetType = FieldType.STRING, sourceCollectionType = CollectionType.ALL, targetCollectionType = CollectionType.NONE)
    public static String concatenate(Action action, Object input) {
        if (action == null || !(action instanceof Concatenate)) {
            throw new IllegalArgumentException("Action must be a Concatenate action");
        }

        if (input == null) {
            return null;
        }

        Concatenate concat = (Concatenate) action;
        String delim = concat.getDelimiter() == null ? "" : concat.getDelimiter();

        Collection<?> inputs = collection(input);

        StringBuilder builder = new StringBuilder();
        for (Object entry : inputs) {
            if (builder.length() > 0) {
                builder.append(delim);
            }
            if (entry != null) {
                builder.append(entry.toString());
            }
        }

        return builder.toString();
    }

    @AtlasFieldActionInfo(name = "CurrentDate", sourceType = FieldType.ALL, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
    public static String currentDate(Action action, Object input) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date()).toString();
    }

    @AtlasFieldActionInfo(name = "CurrentTime", sourceType = FieldType.ALL, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
    public static String currentTime(Action action, Object input) {
        DateFormat df = new SimpleDateFormat("HH:mm:ss");
        return df.format(new Date()).toString();
    }

    @AtlasFieldActionInfo(name = "CurrentDateTime", sourceType = FieldType.ALL, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
    public static String currentDateTime(Action action, Object input) {
        TimeZone tz = TimeZone.getDefault();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);
        return df.format(new Date());
    }

<<<<<<< c4b30f5503986f14c5fa837312c60e2da1e957da
    @AtlasFieldActionInfo(name = "EndsWith", sourceType = FieldType.STRING, targetType = FieldType.BOOLEAN, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
=======
    @AtlasFieldActionInfo(name = "EndsWith", sourceType = FieldType.STRING, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
>>>>>>> Issue #153: Implement String-related p0 field actions
    public static Boolean endsWith(Action action, String input) {
        if (action == null || !(action instanceof EndsWith)) {
            throw new IllegalArgumentException("Action must be an EndsWith action");
        }

        EndsWith endsWith = (EndsWith) action;

        if (endsWith.getString() == null) {
            throw new IllegalArgumentException("EndsWith must be specfied with a string");
        }

        return input == null ? false : input.endsWith(endsWith.getString());
    }

    @AtlasFieldActionInfo(name = "Format", sourceType = FieldType.STRING, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
    public static String format(Action action, String input) {
        if (action == null || !(action instanceof Format)) {
            throw new IllegalArgumentException("Action must be an Format action");
        }

        Format format = (Format) action;

        if (format.getTemplate() == null) {
            throw new IllegalArgumentException("Format must be specfied with a template");
        }

        return String.format(format.getTemplate(), input);
    }

<<<<<<< c4b30f5503986f14c5fa837312c60e2da1e957da
    @AtlasFieldActionInfo(name = "IndexOf", sourceType = FieldType.STRING, targetType = FieldType.NUMBER, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
=======
    @AtlasFieldActionInfo(name = "IndexOf", sourceType = FieldType.STRING, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
>>>>>>> Issue #153: Implement String-related p0 field actions
    public static Number indexOf(Action action, String input) {
        if (action == null || !(action instanceof IndexOf)) {
            throw new IllegalArgumentException("Action must be an IndexOf action");
        }

        IndexOf indexOf = (IndexOf) action;

        if (indexOf.getString() == null) {
            throw new IllegalArgumentException("IndexOf must be specfied with a string");
        }

        return input == null ? -1 : input.indexOf(indexOf.getString());
    }

<<<<<<< c4b30f5503986f14c5fa837312c60e2da1e957da
    @AtlasFieldActionInfo(name = "LastIndexOf", sourceType = FieldType.STRING, targetType = FieldType.NUMBER, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
=======
    @AtlasFieldActionInfo(name = "LastIndexOf", sourceType = FieldType.STRING, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
>>>>>>> Issue #153: Implement String-related p0 field actions
    public static Number lastIndexOf(Action action, String input) {
        if (action == null || !(action instanceof LastIndexOf)) {
            throw new IllegalArgumentException("Action must be a LastIndexOf action");
        }

        LastIndexOf lastIndexOf = (LastIndexOf) action;

        if (lastIndexOf.getString() == null) {
            throw new IllegalArgumentException("LastIndexOf must be specfied with a string");
        }

        return input == null ? -1 : input.lastIndexOf(lastIndexOf.getString());
    }

    @AtlasFieldActionInfo(name = "PadStringRight", sourceType = FieldType.STRING, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
    public static String padStringRight(Action action, String input) {
        String output = input == null ? "" : input;

        if (action == null || !(action instanceof PadStringRight) || ((PadStringRight) action).getPadCharacter() == null
                || ((PadStringRight) action).getPadCount() == null) {
            throw new IllegalArgumentException("PadStringRight must be specfied with padCharacter and padCount");
        }

        PadStringRight padStringRight = (PadStringRight) action;

        for (int i = 0; i < padStringRight.getPadCount(); i++) {
            output = output + padStringRight.getPadCharacter();
        }

        return output;
    }

    @AtlasFieldActionInfo(name = "PadStringLeft", sourceType = FieldType.STRING, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
    public static String padStringLeft(Action action, String input) {
      String output = input == null ? "" : input;

        if (action == null || !(action instanceof PadStringLeft) || ((PadStringLeft) action).getPadCharacter() == null
                || ((PadStringLeft) action).getPadCount() == null) {
            throw new IllegalArgumentException("PadStringLeft must be specfied with padCharacter and padCount");
        }

        PadStringLeft padStringLeft = (PadStringLeft) action;

        for (int i = 0; i < padStringLeft.getPadCount(); i++) {
            output = padStringLeft.getPadCharacter() + output;
        }

        return output;
    }

    @AtlasFieldActionInfo(name = "ReplaceAll", sourceType = FieldType.STRING, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
    public static String replaceAll(Action action, String input) {
        if (action == null || !(action instanceof ReplaceAll)) {
            throw new IllegalArgumentException("Action must be a ReplaceAll action");
        }

        ReplaceAll replaceAll = (ReplaceAll) action;
        String oldString = replaceAll.getOldString();
        if (oldString == null || oldString.length() == 0) {
            throw new IllegalArgumentException("ReplaceAll action must be specified with a non-empty old string");
        }

        String newString = replaceAll.getNewString();
        return input == null ? null : input.replaceAll(oldString, newString == null ? "" : newString);
    }

    @AtlasFieldActionInfo(name = "ReplaceFirst", sourceType = FieldType.STRING, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
    public static String replaceFirst(Action action, String input) {
        if (action == null || !(action instanceof ReplaceFirst)) {
            throw new IllegalArgumentException("Action must be a ReplaceFirst action");
        }

        ReplaceFirst replaceFirst = (ReplaceFirst) action;
        String oldString = replaceFirst.getOldString();
        if (oldString == null || oldString.length() == 0) {
            throw new IllegalArgumentException("ReplaceFirst action must be specified with a non-empty old string");
        }

        String newString = replaceFirst.getNewString();
        return input == null ? null : input.replaceFirst(oldString, newString == null ? "" : newString);
    }

<<<<<<< c4b30f5503986f14c5fa837312c60e2da1e957da
    @AtlasFieldActionInfo(name = "StartsWith", sourceType = FieldType.STRING, targetType = FieldType.BOOLEAN, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
=======
    @AtlasFieldActionInfo(name = "StartsWith", sourceType = FieldType.STRING, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
>>>>>>> Issue #153: Implement String-related p0 field actions
    public static Boolean startsWith(Action action, String input) {
        if (action == null || !(action instanceof StartsWith)) {
            throw new IllegalArgumentException("Action must be an StartsWith action");
        }

        StartsWith startsWith = (StartsWith) action;

        if (startsWith.getString() == null) {
            throw new IllegalArgumentException("StartsWith must be specfied with a string");
        }

        return input == null ? false : input.startsWith(startsWith.getString());
    }

    @AtlasFieldActionInfo(name = "SubString", sourceType = FieldType.STRING, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
    public static String subString(Action action, String input) {
        if (input == null || input.length() == 0) {
            return input;
        }

        if (action == null || !(action instanceof SubString) || ((SubString) action).getStartIndex() == null
                || ((SubString) action).getStartIndex() < 0) {
            throw new IllegalArgumentException("SubString action must be specified with a positive startIndex");
        }

        SubString subString = (SubString) action;
        return doSubString(input, subString.getStartIndex(), subString.getEndIndex());
    }

    @AtlasFieldActionInfo(name = "SubStringAfter", sourceType = FieldType.STRING, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
    public static String subStringAfter(Action action, String input) {
        if (input == null || input.length() == 0) {
            return input;
        }

        if (action == null || !(action instanceof SubStringAfter) || ((SubStringAfter) action).getStartIndex() == null
                || ((SubStringAfter) action).getStartIndex() < 0 || ((SubStringAfter) action).getMatch() == null
                || (((SubStringAfter) action).getEndIndex() != null
                        && ((SubStringAfter) action).getEndIndex() > ((SubStringAfter) action).getStartIndex())) {
            throw new IllegalArgumentException(
                    "SubStringAfter action must be specified with a positive startIndex and a string to match");
        }

        SubStringAfter subStringAfter = (SubStringAfter) action;

        int idx = input.indexOf(subStringAfter.getMatch());
        if (idx < 0) {
            return input;
        }
        idx = idx + subStringAfter.getMatch().length();
        return doSubString(input.substring(idx), subStringAfter.getStartIndex(), subStringAfter.getEndIndex());
    }

    @AtlasFieldActionInfo(name = "SubStringBefore", sourceType = FieldType.STRING, targetType = FieldType.STRING, sourceCollectionType = CollectionType.NONE, targetCollectionType = CollectionType.NONE)
    public static String subStringBefore(Action action, String input) {
        if (input == null || input.length() == 0) {
            return input;
        }

        if (action == null || !(action instanceof SubStringBefore) || ((SubStringBefore) action).getStartIndex() == null
                || ((SubStringBefore) action).getStartIndex() < 0 || ((SubStringBefore) action).getMatch() == null
                || (((SubStringBefore) action).getEndIndex() != null
                        && ((SubStringBefore) action).getEndIndex() > ((SubStringBefore) action).getStartIndex())) {
            throw new IllegalArgumentException(
                    "SubStringBefore action must be specified with a positive startIndex and a string to match");
        }

        SubStringBefore subStringBefore = (SubStringBefore) action;

        int idx = input.indexOf(subStringBefore.getMatch());
        if (idx < 0) {
            return input;
        }

        return doSubString(input.substring(0, idx), subStringBefore.getStartIndex(), subStringBefore.getEndIndex());
    }

    private static Collection<?> collection(Object input) {
        if (input instanceof Collection) {
            return (Collection<?>) input;
        }
        if (input instanceof Map) {
            return ((Map<?, ?>) input).values();
        }
        if (input.getClass().isArray()) {
            return Arrays.asList((Object[]) input);
        }
        throw new IllegalArgumentException(
                "Illegal input[" + input + "]. Input must be a Collection, Map or array");
    }

    private static String doSubString(String input, Integer startIndex, Integer endIndex) {
        if (endIndex == null) {
            return input.substring(startIndex);
        }

        return input.substring(startIndex, endIndex);
    }
}