package org.sunt.query.model.sql

interface Statement

interface SelectItem : Statement

class PhysicalColumn() : SelectItem

