-- File: src/main/resources/db/migration/V2__add_indexes.sql

-- =====================================================
-- Full Text Search Indexes (PostgreSQL GIN)
-- =====================================================

CREATE INDEX idx_journal_title
ON journal_entries
USING GIN (to_tsvector('english', title));

CREATE INDEX idx_journal_content
ON journal_entries
USING GIN (to_tsvector('english', content));

-- =====================================================
-- Sort Optimization
-- =====================================================

CREATE INDEX idx_journal_created_at
ON journal_entries(created_at DESC);